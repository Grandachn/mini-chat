package com.example.minichat.controller.req;

import lombok.Data;

import java.util.Date;

/**
 * @Author by guanda
 * @Date 2018/8/21 15:26
 */
@Data
public class UserInfoReq {
    private String uid;
    /**
     * 性别：1男 2女
     */
    private Integer gender;
    /**
     * 出生日期
     */
    private Date birthday;
    private String phone;
    /**
     * 1单身 2热恋 3已婚 4离异
     */
    private Integer marriage;
}
