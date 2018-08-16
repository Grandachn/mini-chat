package com.example.minichat.handler.message;

import lombok.Builder;
import lombok.Data;

/**
 * @Author by guanda
 * @Date 2018/8/7 16:53
 */

@Data
@Builder
public class MatchMakerStatusMsg {
    private String mid;
    private Boolean status;
}
