package com.example.minichat.controller.rsp;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author by guanda
 * @Date 2018/8/21 15:26
 */
@Data
@Builder
public class UserInfoRsp {
    private String uid;
    /**
     * 性别：1男 2女
     */
    private String gender;
    /**
     * 出生日期
     */
    private Integer age;
    /**
     * 1单身 2热恋 3已婚 4离异
     */
    private String marriage;

    /**
     * 便签
     */
    private String memo;

    /**
     * 是否验证状态
     */
    private Integer status;
}
