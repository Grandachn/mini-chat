package com.example.minichat.entity;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptAddr() {
        return deptAddr;
    }

    public void setDeptAddr(String deptAddr) {
        this.deptAddr = deptAddr;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BoxPosition{" +
        ", id=" + id +
        ", addr=" + addr +
        ", deptId=" + deptId +
        ", deptName=" + deptName +
        ", deptAddr=" + deptAddr +
        ", distance=" + distance +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
