package com.example.minichat.handler;

import com.example.minichat.cons.EventName;
import com.example.minichat.core.exception.BusinessException;
import com.example.minichat.handler.message.CallAnswerMsg;
import com.example.minichat.handler.message.CallMsg;
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
public class CallHandler extends WebSocketMsgHandler{

    private static final Logger log = LoggerFactory.getLogger(CallHandler.class);

    @Override
    public void handle(Object message,
                       Session session,
                       ConcurrentHashMap<String, Session> matchMakerSessionMap,
                       ConcurrentHashMap<String, Session> userSessionMap,
                       ConcurrentHashMap<String, String> sessionIdToUserIdMap,
                       ConcurrentHashMap<String, AtomicBoolean> matchMakerStatusMap,
                       ConcurrentHashMap<String, String> userToMatchMakerMap) throws Exception{

        if (message instanceof CallMsg){
            CallMsg callMsg = (CallMsg)message;
            //抢占接通红娘
            Boolean grabFlag = false;
            AtomicBoolean status = matchMakerStatusMap.get(callMsg.getMid());

            if (status.compareAndSet(true, false)){
                //抢占成功
                String uid = sessionIdToUserIdMap.get(session.getId());
                userToMatchMakerMap.put(uid, callMsg.getMid());
                log.info("user:{} is success connect matchMaker:{}", uid, callMsg.getMid());
                noticeAllUserMatchMakerStatus(callMsg.getMid(), false, userSessionMap);
                grabFlag = true;
            }
            callAnswer(callMsg.getMid(), grabFlag, session, matchMakerSessionMap);
        } else {
            throw new BusinessException("消息格式不正确");
        }
    }

    /**
     * 用戶call的答复 （连线抢占失败只通知用户，成功通知用户和对应的红娘）
     * @param mid
     * @param grabFlag 连线成功为true ，失败为false
     */
    private void callAnswer(String mid,
                            Boolean grabFlag,
                            Session session,
                            ConcurrentHashMap<String, Session> matchMakerSessionMap){
        CallAnswerMsg msg = CallAnswerMsg.builder().mid(mid).grabFlag(grabFlag).build();
        WebSocketMsg webSocketMsg = WebSocketMsg.builder().eventName(EventName.USER_CALL_ANSWER).data(msg).build();
        sendMessage(session, webSocketMsg);
        if (grabFlag){
            sendMessage(matchMakerSessionMap.get(mid), webSocketMsg);
        }
    }
}
