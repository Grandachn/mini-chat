package com.example.minichat.handler;

import com.example.minichat.cons.EventName;
import com.example.minichat.handler.message.MatchMakerStatusMsg;
import com.example.minichat.handler.message.WebSocketMsg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author by guanda
 * @Date 2018/8/6 17:35
 */
public abstract class WebSocketMsgHandler {
    private static final Logger log = LoggerFactory.getLogger(WebSocketMsgHandler.class);

    abstract public void handle(Object message,
                         Session session,
                         ConcurrentHashMap<String, Session> matchMakerSessionMap,
                         ConcurrentHashMap<String, Session> userSessionMap,
                         ConcurrentHashMap<String, String> sessionIdToUserIdMap,
                         ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap,
                         ConcurrentHashMap<String, String> userToMatchMakerMap) throws Exception;

    /**
     * 发送消息
     * @param session
     * @param message
     */
    protected void sendMessage(Session session, WebSocketMsg message){
        ObjectMapper mapper = new ObjectMapper();
        String msgJsonString = "";
        try {
            msgJsonString = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error("json格式化消息异常", e);
        }
        try {
            session.getBasicRemote().sendText(msgJsonString);
        } catch (IOException e) {
            log.error("发送消息IO异常", e);
        }
    }

    /**
     * 通知所有的用戶改变红娘空闲状态
     * @param mid 红娘id
     * @param status 状态
     */
    protected void noticeAllUserMatchMakerStatus(String mid,
                                               Boolean status,
                                               ConcurrentHashMap<String, Session> userSessionMap){
        MatchMakerStatusMsg msg = MatchMakerStatusMsg.builder().mid(mid).status(status).build();
        WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.MATCHMAKER_CHANGE_STATUS).data(msg).build();
        userSessionMap.values().forEach(session ->sendMessage(session, webSocketMsg));
    }

}
