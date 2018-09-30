package com.example.minichat.controller;

import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.ChickenSoup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author by guanda
 * @Date 2018/9/30 13:38
 */
@RestController
@RequestMapping("/heart")
public class HeartController {
    @GetMapping()
    public Result<ChickenSoup> getHeart() {
        return Result.success();
    }
}
