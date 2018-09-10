package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.BoxPosition;
import com.example.minichat.entity.Label;
import com.example.minichat.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author granda
 * @since 2018-09-10
 */
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping()
    public Result<List<Label>> getUserInfo() {
        List<Label> labels = labelService.selectList(new EntityWrapper<>());
        return Result.success(labels);
    }
}

