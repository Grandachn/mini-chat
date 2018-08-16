package com.example.minichat.handler.message;

import lombok.Builder;
import lombok.Data;

/**
 * @Author by guanda
 * @Date 2018/8/7 20:02
 */
@Data
@Builder
public class CallAnswerMsg {
    /**
     * 红娘id
     */
    private String mid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 连线是否成功标志
     */
    private Boolean grabFlag;
}
