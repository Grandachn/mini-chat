package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.controller.req.UserInfoReq;
import com.example.minichat.controller.rsp.UserInfoRsp;
import com.example.minichat.core.dto.ErrorResult;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.UserInfo;
import com.example.minichat.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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

    @GetMapping("/uid/get")
    public Result<String> getUid(){
        return Result.success(UUID.randomUUID().toString());
    }

    @GetMapping("/{uid}")
    public Result<UserInfoRsp> getUserInfo(@PathVariable String uid){
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("uid", uid));
        if (null != userInfo){
            return Result.success(entityToRsp(userInfo));
        }
        return Result.error(ErrorResult.SMS_SEND_ERROR);
    }

    private UserInfoRsp entityToRsp(UserInfo userInfo){
        UserInfoRsp userInfoRsp = UserInfoRsp.builder().uid(userInfo.getUid()).build();
        //年龄
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        cal.setTime(userInfo.getBirthday());
        int yearBirth = cal.get(Calendar.YEAR);
        int age = yearNow - yearBirth;
        userInfoRsp.setAge(age);

        if (userInfo.getGender().equals(1)){
            userInfoRsp.setGender("男");
        }else if (userInfo.getGender().equals(2)){
            userInfoRsp.setGender("女");
        }else {
            userInfoRsp.setGender("未知");
        }

       // 1单身 2热恋 3已婚 4离异
        if (userInfo.getMarriage().equals(1)){
            userInfoRsp.setMarriage("单身");
        }else if (userInfo.getMarriage().equals(2)){
            userInfoRsp.setMarriage("热恋");
        }else if (userInfo.getMarriage().equals(3)){
            userInfoRsp.setMarriage("已婚");
        }else if (userInfo.getMarriage().equals(4)){
            userInfoRsp.setMarriage("离异");
        }else {
            userInfoRsp.setMarriage("未知");
        }

        if (null != userInfo.getPhone() && !userInfo.getPhone().equals("")){
            userInfoRsp.setStatus(1);
        }else {
            userInfoRsp.setStatus(0);
        }

        userInfoRsp.setMemo("测试便签内容");
        return userInfoRsp;
    }
}

