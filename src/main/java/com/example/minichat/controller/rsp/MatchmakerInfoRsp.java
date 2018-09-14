package com.example.minichat.controller.rsp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author by guanda
 * @Date 2018/9/14 14:02
 */
@Data
@NoArgsConstructor
public class MatchmakerInfoRsp {
    /**
     * 红娘id
     */
    private String uid;
    /**
     * 名字
     */
    private String name;
    /**
     * 工号
     */
    private String workerId;
    /**
     * 性别：1男 2女
     */
    private Integer gender;
    /**
     * 红娘描述
     */
    private String detail;
    /**
     * 红娘照片url
     */
    private String picUrl;

    /**
     * 微信二维码url
     */
    private String wechatUrl;
    /**
     * 电话
     */
    private String phone;
}
