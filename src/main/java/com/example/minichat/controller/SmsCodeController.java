package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.core.dto.ErrorResult;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.SmsCode;
import com.example.minichat.service.SmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author granda
 * @since 2018-09-05
 */
@RestController
@RequestMapping("/smsCode")
public class SmsCodeController {

    @Autowired
    private SmsCodeService smsCodeService;

    private Pattern pattern = Pattern.compile("1\\d{10}");

    @GetMapping("/{phone}")
    public Result<Void> sendVerifySms(@PathVariable String phone) {
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            return Result.error(ErrorResult.ILLEGAL_PARAMS);
        }
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        SmsCode smsCode = smsCodeService.selectOne(new EntityWrapper<SmsCode>().eq("phone", phone));
        if (null == smsCode){
            smsCode = new SmsCode();
            smsCode.setPhone(phone);
        }
        smsCode.setCode(verifyCode);
        smsCodeService.insertOrUpdate(smsCode);
        return Result.success();
    }
    @GetMapping("/{phone}/{code}")
    public Result<Void> checkVerifySms(@PathVariable String phone, @PathVariable String code) {
        SmsCode smsCode = smsCodeService.selectOne(new EntityWrapper<SmsCode>().eq("phone", phone));
        if (null != smsCode && smsCode.getCode().equals(code)){
            return Result.success();
        }
        return  Result.error(ErrorResult.SMS_VERIFY_ERROR);
    }
}

