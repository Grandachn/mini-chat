package com.example.minichat.handler;

import com.example.minichat.cons.EventName;
import com.example.minichat.core.exception.BusinessException;
import com.example.minichat.handler.message.JoinMsg;
import com.example.minichat.handler.message.OfferMsg;
import com.example.minichat.handler.message.WebSocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author by guanda
 * @Date 2018/8/13 13:50
 */
public class JoinHandler extends WebSocketMsgHandler {

    private static final Logger log = LoggerFactory.getLogger(JoinHandler.class);

    @Override
    public void handle(Object message,
                       Session session,
                       ConcurrentHashMap<String, Session> matchMakerSessionMap,
                       ConcurrentHashMap<String, Session> userSessionMap,
                       ConcurrentHashMap<String, String> sessionIdToUserIdMap,
                       ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap,
                       ConcurrentHashMap<String, String> userToMatchMakerMap) throws Exception {

        if (message instanceof JoinMsg){
            JoinMsg joinMsg = (JoinMsg)message;
            log.info("JoinHandler is doing");
            WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.GET_ALL_MATCHMAKER_STATE).data(matchMakerStatusMap).build();
            sendMessage(session, webSocketMsg);

        } else {
            throw new BusinessException("消息格式不正确");
        }
    }
}

