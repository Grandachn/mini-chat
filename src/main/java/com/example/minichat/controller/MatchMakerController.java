package com.example.minichat.controller;

import com.example.minichat.controller.req.MatchmakerOnlineReq;
import com.example.minichat.core.dto.Result;
import com.example.minichat.service.MatchMakerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Author by guanda
 * @Date 2018/8/6 16:04
 */
@Controller
@RequestMapping("/matchmaker")
public class MatchMakerController {

    @Resource
    MatchMakerService matchMakerService;

    @PostMapping("/online")
    private Result<Void> addMatchmakerOnline(MatchmakerOnlineReq matchmakerOnlineReq){
        matchMakerService.addMatchMakerOnline(matchmakerOnlineReq.getMid(), matchmakerOnlineReq.getSocket());
        return Result.success();
    }

    //get

    //delete

}
