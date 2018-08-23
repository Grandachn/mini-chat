package com.example.minichat.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.minichat.controller.req.NoteReq;
import com.example.minichat.core.dto.ErrorResult;
import com.example.minichat.core.dto.Result;
import com.example.minichat.entity.Note;
import com.example.minichat.service.NoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @since 2018-08-23
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public Result<Void> addNote(@RequestBody NoteReq noteReq){
        Note note = noteService.selectOne(new EntityWrapper<Note>().eq("uid", noteReq.getUid()));
        if (null == note){
            note = new Note();
        }
        BeanUtils.copyProperties(noteReq, note);
        noteService.insertOrUpdate(note);
        return Result.success();
    }
}

