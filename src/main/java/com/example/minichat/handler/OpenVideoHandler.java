package com.example.minichat.handler;

import com.example.minichat.cons.EventName;
import com.example.minichat.core.exception.BusinessException;
import com.example.minichat.handler.message.CloseVideoMsg;
import com.example.minichat.handler.message.OpenVideoMsg;
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
public class OpenVideoHandler extends WebSocketMsgHandler {

    private static final Logger log = LoggerFactory.getLogger(OpenVideoHandler.class);

    @Override
    public void handle(Object message,
                       Session session,
                       ConcurrentHashMap<String, Session> matchMakerSessionMap,
                       ConcurrentHashMap<String, Session> userSessionMap,
                       ConcurrentHashMap<String, String> sessionIdToUserIdMap,
                       ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap,
                       ConcurrentHashMap<String, String> userToMatchMakerMap) throws Exception {

        if (message instanceof OpenVideoMsg){
            OpenVideoMsg openVideoMsg = (OpenVideoMsg)message;
            String mid = userToMatchMakerMap.get(openVideoMsg.getUid());
            WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.OPEN_VIDEO).data(openVideoMsg).build();
            log.info("send openVideoMsg to matchMaker:{}", mid);
            sendMessage(matchMakerSessionMap.get(mid), webSocketMsg);
        } else {
            throw new BusinessException("消息格式不正确");
        }

    }
}
