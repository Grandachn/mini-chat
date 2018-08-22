package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.controller.req.LoginReq;
import com.example.minichat.core.dto.ErrorResult;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.MatchmakerInfo;
import com.example.minichat.service.MatchmakerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author granda
 * @since 2018-08-21
 */
@RestController
@RequestMapping("/matchmakerInfo")
public class MatchmakerInfoController {

    @Autowired
    private MatchmakerInfoService matchmakerInfoService;

    @PostMapping("/login")
    public Result<Void> login(@RequestBody LoginReq loginReq){
        MatchmakerInfo matchmakerInfo = matchmakerInfoService.selectOne(new EntityWrapper<MatchmakerInfo>()
                .eq("worker_id",loginReq.getMid()));
        if (null != matchmakerInfo && loginReq.getPassword().equals(matchmakerInfo.getPassword())){
            return Result.success();
        }
        return Result.error(ErrorResult.WRONG_PASSWORD);
    }

    @GetMapping("/{mid}")
    public Result<MatchmakerInfo> getMatchmakerInfo(@PathVariable String mid){
        MatchmakerInfo matchmakerInfo = matchmakerInfoService.selectOne(new EntityWrapper<MatchmakerInfo>()
                .eq("worker_id", mid));
        return Result.success(matchmakerInfo);
    }
}

