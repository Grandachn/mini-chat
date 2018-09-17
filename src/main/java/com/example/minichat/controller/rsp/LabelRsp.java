package com.example.minichat.controller.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author granda
 * @since 2018-09-10
 */
@Data
public class LabelRsp{
    private Integer id;
    /**
     * 标签内容
     */
    private String content;
}
