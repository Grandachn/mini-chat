package com.example.minichat.handler;

import com.example.minichat.cons.EventName;
import com.example.minichat.core.exception.BusinessException;
import com.example.minichat.handler.message.SureCallAnswerMsg;
import com.example.minichat.handler.message.SureCallMsg;
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
public class SureCallHandler extends WebSocketMsgHandler{

    private static final Logger log = LoggerFactory.getLogger(SureCallHandler.class);

    @Override
    public void handle(Object message,
                       Session session,
                       ConcurrentHashMap<String, Session> matchMakerSessionMap,
                       ConcurrentHashMap<String, Session> userSessionMap,
                       ConcurrentHashMap<String, String> sessionIdToUserIdMap,
                       ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap,
                       ConcurrentHashMap<String, String> userToMatchMakerMap) throws Exception{

        if (message instanceof SureCallMsg){
            SureCallMsg sureCallMsg = (SureCallMsg)message;
            if(sureCallMsg.getGrabFlag() == false){
                matchMakerStatusMap.put(sureCallMsg.getMid(), new AtomicBoolean(true));
            }
            callAnswer(sureCallMsg.getMid(), sureCallMsg.getUid(), sureCallMsg.getUserId(), sureCallMsg.getGrabFlag(), matchMakerSessionMap, userSessionMap);
        } else {
            throw new BusinessException("消息格式不正确");
        }
    }

    /**
     * 用戶call的答复 （连线成功通知用户和对应的红娘）
     * @param mid
     */
    private void callAnswer(String mid,
                            String uid,
                            String userId,
                            Boolean flag,
                            ConcurrentHashMap<String, Session> matchMakerSessionMap,
                            ConcurrentHashMap<String, Session> userSessionMap){
        SureCallAnswerMsg msg = SureCallAnswerMsg.builder().mid(mid).uid(uid).userId(userId).grabFlag(flag).build();
        WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.USER_SURE_CALL_ANSWER).data(msg).build();
        log.info("send message :{} to userSessionId:{}", webSocketMsg, uid);
        sendMessage(userSessionMap.get(uid), webSocketMsg);

        webSocketMsg = WebSocketMsg.builder().eventName(EventName.MATCHMAKER_SURE_CALL_ANSWER).data(msg).build();
        log.info("send message :{} to matchmakerId:{}", webSocketMsg, mid);
        sendMessage(matchMakerSessionMap.get(mid), webSocketMsg);
    }
}
