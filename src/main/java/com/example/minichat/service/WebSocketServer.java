package com.example.minichat.service;

import com.example.minichat.cons.EventName;
import com.example.minichat.handler.WebSocketMsgHandler;
import com.example.minichat.handler.message.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author by guanda
 * @Date 2018/8/6 17:07
 *
 *   type: 1.红娘 2.用户
 *   id : 红娘or用户id
 */
@ServerEndpoint(value = "/websocket/{type}/{id}")
@Component
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private static final int TYPE_MATCHMAKER = 1;
    private static final int TYPE_USER = 2;

    /**
     * 维护红娘id，Session
     */
    private static ConcurrentHashMap<String, Session> matchMakerSessionMap = new ConcurrentHashMap<>();

    /**
     * 维护用户id，Session
     */
    private static ConcurrentHashMap<String, Session> userSessionMap = new ConcurrentHashMap<>();

    /**
     * 维护Session id, 用户（红娘）id ，用于session反查用户（红娘）id
     */
    private static ConcurrentHashMap<String, String> sessionIdToUserIdMap = new ConcurrentHashMap<>();

    /**
     * 维护红娘id，空闲状态（ture：空闲， false：忙碌）
     */
    private static ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap = new ConcurrentHashMap<>();

    /**
     * 维护正在通话的用户id，红娘id
     */
    private static ConcurrentHashMap<String, String> userToMatchMakerMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;


    /**
     * 连接建立成功调用的方法
     * @param type
     * @param id
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("type") Integer type,
                       @PathParam("id") String id,
                       Session session) {
        this.session = session;
        if (type.equals(TYPE_MATCHMAKER)){
            //红娘接入
            log.info("matchMaker:{} is connect", id);
            matchMakerSessionMap.put(id, session);
            matchMakerStatusMap.put(id, new AtomicBoolean(true));
            noticeAllUserMatchMakerStatus(id, true);
        } else if (type.equals(TYPE_USER)){
            //用户接入
            log.info("user:{} is connect", id);
            userSessionMap.put(id, session);
//            getALLMatchMakerStatus();
            connectSuccess();
        }

        sessionIdToUserIdMap.put(session.getId(), id);
    }

    /**
     * 通知所有的用戶改变红娘空闲状态
     * @param mid 红娘id
     * @param status 状态
     */
    private void noticeAllUserMatchMakerStatus(String mid, Boolean status){
        MatchMakerStatusMsg msg = MatchMakerStatusMsg.builder().mid(mid).status(status).build();
        WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.MATCHMAKER_CHANGE_STATUS).data(msg).build();
        userSessionMap.values().forEach(session ->sendMessage(session, webSocketMsg));
    }

    /**
     * 获取所有红娘空闲状态
     */
    private void getALLMatchMakerStatus(){
        WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.GET_ALL_MATCHMAKER_STATE).data(matchMakerStatusMap).build();
        log.info("send message:{}", webSocketMsg.toString());
        sendMessage(this.session, webSocketMsg);
    }

    /**
     * 获取所有红娘空闲状态
     */
    private void connectSuccess(){
        WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.CONNECT_SUCCESS).build();
        log.info("send message:{}", webSocketMsg.toString());
        sendMessage(this.session, webSocketMsg);
    }

    private void sendMessage(Session session,WebSocketMsg message){
        ObjectMapper mapper = new ObjectMapper();
        String msgJsonString = "";
        try {
            msgJsonString = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("json格式化消息异常");
        }
        try {
            session.getBasicRemote().sendText(msgJsonString);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("发送消息IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        String uid = close();
        log.info("uid:" + uid + "关闭连接");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param content 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String content, Session session) throws Exception {
        log.info("来自客户端的消息:" + content);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(content);
        handleEvent(node.get("eventName").asText(),  content);
    }

    /**
     * 处理消息
     * @param eventName
     * @param content
     * @throws Exception
     */
    private void handleEvent(String eventName, String content) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String s = content.split("data\":")[1];
        s = s.substring(0, s.length() - 1);
        Object message = mapper.readValue(s, Class.forName("com.example.minichat.handler.message." + eventName + "Msg"));
        WebSocketMsgHandler msgHandler = (WebSocketMsgHandler)Class.forName("com.example.minichat.handler." + eventName + "Handler").newInstance();
        msgHandler.handle(message,
                this.session,
                matchMakerSessionMap,
                userSessionMap,
                sessionIdToUserIdMap,
                matchMakerStatusMap,
                userToMatchMakerMap);
    }

    /**
     * 出错
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        String uid = close();
        log.error("uid:" + uid + "发生错误", error);
    }



    private String close(){
        String uid = sessionIdToUserIdMap.get(this.session.getId());

        if (matchMakerSessionMap.containsKey(uid)){
            noticeAllUserMatchMakerStatus(uid, false);
        }

        sessionIdToUserIdMap.remove(this.session.getId());
        matchMakerSessionMap.remove(uid);
        matchMakerStatusMap.remove(uid);
        userSessionMap.remove(uid);
        return uid;
    }

}
