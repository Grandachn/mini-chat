package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.controller.rsp.LabelRsp;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.Label;
import com.example.minichat.entity.Version;
import com.example.minichat.service.VersionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author granda
 * @since 2018-09-18
 */
@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    private VersionService versionService;

    @GetMapping()
    public Result<Version> getUserInfo() {
        Version version = versionService.selectOne(new EntityWrapper<Version>().eq("id", 0));

        return Result.success(version);
    }
}

