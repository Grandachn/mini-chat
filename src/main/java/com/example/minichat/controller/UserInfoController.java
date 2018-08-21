package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.controller.req.UserInfoReq;
import com.example.minichat.core.dto.ErrorResult;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.UserInfo;
import com.example.minichat.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author granda
 * @since 2018-08-21
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping
    public Result<Void> addUserInfo(@RequestBody UserInfoReq userInfoReq){
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("uid", userInfoReq.getUid()));
        if (null == userInfo){
            userInfo = new UserInfo();
        }
        BeanUtils.copyProperties(userInfoReq, userInfo);
        if(userInfoService.insertOrUpdate(userInfo)){
            return Result.success();
        }
        return Result.error(ErrorResult.UPDATE_USER_INFO_FAIL);
    }

    @GetMapping("/{uid}")
    public Result<UserInfo> getUserInfo(@PathVariable String uid){
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("uid", uid));
        return Result.success(userInfo);
    }
}

