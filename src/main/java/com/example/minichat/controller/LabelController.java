package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.controller.rsp.LabelRsp;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.Label;
import com.example.minichat.service.LabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public Result<List<LabelRsp>> getUserInfo() {
        List<Label> labels = labelService.selectList(new EntityWrapper<Label>().notIn("id", 0));
        List<LabelRsp> labelRspList = new ArrayList<>();
        labels.forEach(e -> {
            LabelRsp labelRsp = new LabelRsp();
            BeanUtils.copyProperties(e, labelRsp);
            labelRspList.add(labelRsp);
        });
        return Result.success(labelRspList);
    }
}

