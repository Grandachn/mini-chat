package com.example.minichat.handler.message;

import lombok.Builder;
import lombok.Data;

/**
 * @Author by guanda
 * @Date 2018/8/6 17:44
 */
@Data
@Builder
public class WebSocketMsg<T> {
    private String eventName;
    private T data;
}
