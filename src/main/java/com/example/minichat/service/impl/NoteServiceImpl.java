package com.example.minichat.service.impl;

import com.example.minichat.entity.Note;
import com.example.minichat.mapper.NoteMapper;
import com.example.minichat.service.NoteService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author granda
 * @since 2018-08-23
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

}
