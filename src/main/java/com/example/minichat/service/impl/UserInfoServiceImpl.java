package com.example.minichat.service.impl;

import com.example.minichat.entity.UserInfo;
import com.example.minichat.mapper.UserInfoMapper;
import com.example.minichat.service.UserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author granda
 * @since 2018-08-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
