package com.example.minichat.handler;

import com.example.minichat.cons.EventName;
import com.example.minichat.core.exception.BusinessException;
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
public class OfferHandler  extends WebSocketMsgHandler {

    private static final Logger log = LoggerFactory.getLogger(OfferHandler.class);

    @Override
    public void handle(Object message,
                       Session session,
                       ConcurrentHashMap<String, Session> matchMakerSessionMap,
                       ConcurrentHashMap<String, Session> userSessionMap,
                       ConcurrentHashMap<String, String> sessionIdToUserIdMap,
                       ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap,
                       ConcurrentHashMap<String, String> userToMatchMakerMap) throws Exception {

        if (message instanceof OfferMsg){
            OfferMsg offerMsg = (OfferMsg)message;
            String id = offerMsg.getToId();
            Thread.sleep(1000);
            WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.OFFER_TO).data(offerMsg).build();
            if (matchMakerSessionMap.containsKey(id)){
                log.info("send offer to matchMaker:{}", id);
                sendMessage(matchMakerSessionMap.get(id), webSocketMsg);
            }else if (userSessionMap.containsKey(id)){
                log.info("send offer to user:{}", id);
                sendMessage(matchMakerSessionMap.get(id), webSocketMsg);
            }else{
                throw new BusinessException("id不存在");
            }
        } else {
            throw new BusinessException("消息格式不正确");
        }

    }
}

