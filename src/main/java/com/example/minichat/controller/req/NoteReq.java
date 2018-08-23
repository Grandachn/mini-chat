package com.example.minichat.controller.req;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author granda
 * @since 2018-08-23
 */
@Data
public class NoteReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String uid;
    /**
     * 红娘id
     */
    private String mid;
    /**
     * 红娘小记
     */
    private String note;


}
