package com.example.minichat.service.impl;

import com.example.minichat.entity.Version;
import com.example.minichat.mapper.VersionMapper;
import com.example.minichat.service.VersionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author granda
 * @since 2018-09-18
 */
@Service
public class VersionServiceImpl extends ServiceImpl<VersionMapper, Version> implements VersionService {

}
