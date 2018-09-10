package com.example.minichat.handler.message;

import lombok.Builder;
import lombok.Data;

/**
 * @Author by guanda
 * @Date 2018/8/13 13:58
 */
@Data
@Builder
public class EndAnswerMsg {
    private Integer byWho;
}
