package com.example.minichat.handler.message;

import lombok.Data;

/**
 * @Author by guanda
 * @Date 2018/8/13 13:48
 */
@Data
public class OfferMsg {
    private Sdp sdp;
    private String fromId;
    private String toId;
}
