package com.example.minichat.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author granda
 * @since 2018-09-10
 */
@Data
public class BoxPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 机器部署地址
     */
    private String addr;
    private Integer deptId;
    /**
     * 对应门店名称
     */
    private String deptName;
    /**
     * 对应门店地址
     */
    private String deptAddr;
    /**
     * 距离门店距离
     */
    private String distance;
    private Date createTime;
    private Date updateTime;


    private String x;
    private String y;
}
