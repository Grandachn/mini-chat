package com.example.minichat.handler.message;

import lombok.Builder;
import lombok.Data;

/**
 * @Author by guanda
 * @Date 2018/8/7 20:02
 */
@Data
@Builder
public class SureCallAnswerMsg {
    /**
     * 红娘id
     */
    private String mid;

    /**
     * 客户端id
     */
    private String uid;

    /**
     * 用户uid
     */
    private String userId;

    /**
     * 连线是否成功标志
     */
    private Boolean grabFlag;
}
