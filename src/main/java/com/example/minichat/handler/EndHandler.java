package com.example.minichat.handler;

import com.example.minichat.cons.EventName;
import com.example.minichat.core.exception.BusinessException;
import com.example.minichat.handler.message.EndMsg;
import com.example.minichat.handler.message.WebSocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author by guanda
 * @Date 2018/8/13 11:57
 */
public class EndHandler extends WebSocketMsgHandler {

    private static final Logger log = LoggerFactory.getLogger(EndHandler.class);

    @Override
    public void handle(Object message,
                       Session session,
                       ConcurrentHashMap<String, Session> matchMakerSessionMap,
                       ConcurrentHashMap<String, Session> userSessionMap,
                       ConcurrentHashMap<String, String> sessionIdToUserIdMap,
                       ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap,
                       ConcurrentHashMap<String, String> userToMatchMakerMap) throws Exception {

        if (message instanceof EndMsg){
            EndMsg endMsg = (EndMsg)message;
            String id = endMsg.getId();
            final String[] uid = new String[1];
            String mid;
            if (matchMakerSessionMap.containsKey(id)){
                mid = id;
                log.info("matchMaker:{} end the chat", id);
//                matchMakerStatusMap.put(id, new AtomicBoolean(true));
                userToMatchMakerMap.forEach((k, v) -> {
                    if(v.equals(id)){
                        uid[0] = k;
                        userToMatchMakerMap.remove(k);
                    }
                });
//                noticeAllUserMatchMakerStatus(mid, true, userSessionMap);
                //通知两端关闭peerConnection
                WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.End_ANSWER).build();
                log.info("send end to user:{}", uid[0]);
                sendMessage(userSessionMap.get(uid[0]), webSocketMsg);
                log.info("send end to matchMaker:{}", mid);
                sendMessage(matchMakerSessionMap.get(mid), webSocketMsg);

            }else if (userSessionMap.containsKey(id)) {
                log.info("user:{} end the chat", id);
                uid[0] = id;
                mid = userToMatchMakerMap.get(id);
                if (null != mid) {
                    matchMakerStatusMap.put(mid, new AtomicBoolean(true));
                    userToMatchMakerMap.remove(id);
                    noticeAllUserMatchMakerStatus(mid, true, userSessionMap);
                    //通知两端关闭peerConnection
                    WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.End_ANSWER).build();
                    log.info("send end to user:{}", uid[0]);
                    sendMessage(userSessionMap.get(uid[0]), webSocketMsg);
                    log.info("send end to matchMaker:{}", mid);
                    sendMessage(matchMakerSessionMap.get(mid), webSocketMsg);
                }
            }
//            }else{
//                throw new BusinessException("id不存在");
//            }

//            noticeAllUserMatchMakerStatus(mid, true, userSessionMap);
//            //通知两端关闭peerConnection
//            WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.End_ANSWER).build();
//            log.info("send end to user:{}", uid[0]);
//            sendMessage(userSessionMap.get(uid[0]), webSocketMsg);
//            log.info("send end to matchMaker:{}", mid);
//            sendMessage(matchMakerSessionMap.get(mid), webSocketMsg);
        } else {
            throw new BusinessException("消息格式不正确");
        }

    }
}
