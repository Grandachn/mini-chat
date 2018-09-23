package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.core.dto.ErrorResult;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.MatchmakerInfo;
import com.example.minichat.entity.SmsCode;
import com.example.minichat.entity.UserInfo;
import com.example.minichat.interceptor.SessionContext;
import com.example.minichat.service.MatchmakerInfoService;
import com.example.minichat.service.SmsCodeService;
import com.example.minichat.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private MatchmakerInfoService matchmakerInfoService;

    @Autowired
    private JavaMailSender javaMailSender;

    private ExecutorService sendMailThread = Executors.newFixedThreadPool(3);

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


        SmsCode finalSmsCode = smsCode;
        sendMailThread.execute(() -> {
            SimpleMailMessage mainMessage = new SimpleMailMessage();
            //发送者
            mainMessage.setFrom("hgd0922@163.com");
            //接收者
            mainMessage.setTo("grandachn@163.com");
            //发送的标题
            mainMessage.setSubject("【珍心面对面】手机号：" + phone);
            //发送的内容
            mainMessage.setText("获取到的验证码为：" + finalSmsCode.getCode());

            javaMailSender.send(mainMessage);
        });

        return Result.success();
    }
    @GetMapping("/{phone}/{code}")
    public Result<Void> checkVerifySms(@PathVariable String phone, @PathVariable String code) {
        SmsCode smsCode = smsCodeService.selectOne(new EntityWrapper<SmsCode>().eq("phone", phone));
        if (null != smsCode && smsCode.getCode().equals(code)){
            String uid = SessionContext.getUid();
            UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("uid", uid));
            if (null == userInfo){
                userInfo = new UserInfo();
                userInfo.setUid(uid);
            }
            userInfo.setPhone(phone);
            userInfoService.insertOrUpdate(userInfo);
            return Result.success();
        }
        return  Result.error(ErrorResult.SMS_VERIFY_ERROR);
    }

    @GetMapping("/sendMidInfo/mid/{mid}")
    public Result<Void> checkVerifySmsAfterChat(@PathVariable String mid) {
        String uid = SessionContext.getUid();
        UserInfo userInfo = userInfoService.selectOne(new EntityWrapper<UserInfo>().eq("uid", uid));

        MatchmakerInfo matchmakerInfo = matchmakerInfoService.selectOne(new EntityWrapper<MatchmakerInfo>().eq("worker_id", mid));

        sendMailThread.execute(() -> {
            SimpleMailMessage mainMessage = new SimpleMailMessage();
            //发送者
            mainMessage.setFrom("hgd0922@163.com");
            //接收者
            mainMessage.setTo("grandachn@163.com");
            //发送的标题
            mainMessage.setSubject("【珍心面对面】手机号：" + userInfo.getPhone());
            //发送的内容
            mainMessage.setText("刚才与您连线的老师是：" + matchmakerInfo.getName() + "，联系电话：" + matchmakerInfo.getPhone());

            javaMailSender.send(mainMessage);
        });

        return Result.success();
    }
}

