package com.example.minichat.handler;

import com.example.minichat.cons.EventName;
import com.example.minichat.core.exception.BusinessException;
import com.example.minichat.handler.message.JoinMsg;
import com.example.minichat.handler.message.MatchMakerStatusMsg;
import com.example.minichat.handler.message.WebSocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author by guanda
 * @Date 2018/9/10 17:03
 */
public class MatchMakerStatusHandler extends WebSocketMsgHandler{
    private static final Logger log = LoggerFactory.getLogger(MatchMakerStatusHandler.class);

    @Override
    public void handle(Object message,
                       Session session,
                       ConcurrentHashMap<String, Session> matchMakerSessionMap,
                       ConcurrentHashMap<String, Session> userSessionMap,
                       ConcurrentHashMap<String, String> sessionIdToUserIdMap,
                       ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap,
                       ConcurrentHashMap<String, String> userToMatchMakerMap) throws Exception {

        if (message instanceof MatchMakerStatusMsg){
            MatchMakerStatusMsg matchMakerStatusMsg = (MatchMakerStatusMsg)message;
            log.info("MatchMakerStatusMsg is change:" + matchMakerStatusMsg.getStatus());
            matchMakerStatusMap.put(matchMakerStatusMsg.getMid(), new AtomicBoolean(matchMakerStatusMsg.getStatus()));
            noticeAllUserMatchMakerStatus(matchMakerStatusMsg.getMid(), true, userSessionMap);
//            WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.GET_ALL_MATCHMAKER_STATE).data(matchMakerStatusMap).build();
//            sendMessage(session, webSocketMsg);
        } else {
            throw new BusinessException("消息格式不正确");
        }
    }
}
