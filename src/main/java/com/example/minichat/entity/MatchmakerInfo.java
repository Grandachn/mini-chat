package com.example.minichat.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author granda
 * @since 2018-08-21
 */
@Data
public class MatchmakerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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
     * 密码
     */
    private String password;
    /**
     * 微信二维码url
     */
    private String wechatUrl;
    /**
     * 电话
     */
    private String phone;

}
