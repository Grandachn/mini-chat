package com.example.minichat.handler.message;

import lombok.Builder;
import lombok.Data;

/**
 * @Author by guanda
 * @Date 2018/8/7 18:59
 */
@Data
@Builder
public class CallMsg {
    /**
     * 红娘id
     */
    private String mid;
}
