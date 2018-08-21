package com.example.minichat.controller.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author by guanda
 * @Date 2018/8/21 21:17
 */
@Data
public class LoginReq {
    private String mid;
    @NotNull
    private String password;
}
