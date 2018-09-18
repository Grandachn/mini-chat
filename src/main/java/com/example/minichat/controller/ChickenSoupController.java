package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.controller.rsp.LabelRsp;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.ChickenSoup;
import com.example.minichat.entity.Label;
import com.example.minichat.service.ChickenSoupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author granda
 * @since 2018-09-18
 */
@RestController
@RequestMapping("/chickenSoup")
public class ChickenSoupController {
    @Autowired
    private ChickenSoupService chickenSoupService;

    @GetMapping()
    public Result<ChickenSoup> getUserInfo() {
        List<ChickenSoup> labels = chickenSoupService.selectList(new EntityWrapper<>());
        return Result.success(labels.get((int) Math.floor(Math.random() * labels.size())));
    }
}

