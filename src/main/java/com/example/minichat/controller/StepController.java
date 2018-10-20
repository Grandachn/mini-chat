package com.example.minichat.controller;


import com.example.minichat.controller.req.StepReq;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.Step;
import com.example.minichat.interceptor.SessionContext;
import com.example.minichat.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author granda
 * @since 2018-10-20
 */
@RestController
@RequestMapping("/step")
public class StepController {

    @Autowired
    StepService stepService;

    @PostMapping
    public Result<Void> add(@RequestBody StepReq stepReq){
        Step step = new Step();
        step.setStep(stepReq.getStep());
        step.setUid(SessionContext.getUid());
        stepService.insert(step);
        return Result.success();
    }
}

