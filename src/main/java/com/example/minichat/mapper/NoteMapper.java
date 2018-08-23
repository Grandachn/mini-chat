package com.example.minichat.mapper;

import com.example.minichat.entity.Note;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author granda
 * @since 2018-08-23
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

}
