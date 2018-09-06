package com.example.minichat.handler;

import com.example.minichat.cons.EventName;
import com.example.minichat.core.exception.BusinessException;
import com.example.minichat.handler.message.CallAnswerMsg;
import com.example.minichat.handler.message.CallMsg;
import com.example.minichat.handler.message.IsOkMsg;
import com.example.minichat.handler.message.WebSocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author by guanda
 * @Date 2018/8/13 10:43
 */
public class IsOkHandler extends WebSocketMsgHandler{

    private static final Logger log = LoggerFactory.getLogger(IsOkHandler.class);

    @Override
    public void handle(Object message,
                       Session session,
                       ConcurrentHashMap<String, Session> matchMakerSessionMap,
                       ConcurrentHashMap<String, Session> userSessionMap,
                       ConcurrentHashMap<String, String> sessionIdToUserIdMap,
                       ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap,
                       ConcurrentHashMap<String, String> userToMatchMakerMap) throws Exception{

        if (message instanceof IsOkMsg){
            IsOkMsg isOkMsg = (IsOkMsg)message;
            if (isOkMsg.getIsOk().equals(true)){
                matchMakerStatusMap.put(isOkMsg.getMid(), new AtomicBoolean(true));
                noticeAllUserMatchMakerStatus(isOkMsg.getMid(), true, userSessionMap);
            }
        } else {
            throw new BusinessException("消息格式不正确");
        }
    }
}
