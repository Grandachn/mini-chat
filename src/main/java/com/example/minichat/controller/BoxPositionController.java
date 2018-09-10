package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.controller.rsp.UserInfoRsp;
import com.example.minichat.core.dto.ErrorResult;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.BoxPosition;
import com.example.minichat.entity.UserInfo;
import com.example.minichat.service.BoxPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author granda
 * @since 2018-09-10
 */
@RestController
@RequestMapping("/boxPosition")
public class BoxPositionController {

    @Autowired
    private BoxPositionService boxPositionService;

    @GetMapping("/{boxId}")
    public Result<BoxPosition> getUserInfo(@PathVariable String boxId) {
        BoxPosition boxPosition = boxPositionService.selectOne(new EntityWrapper<BoxPosition>().eq("id", boxId));
        return Result.success(boxPosition);
    }
}

