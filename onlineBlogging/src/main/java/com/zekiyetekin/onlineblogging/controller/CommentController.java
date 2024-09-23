package com.zekiyetekin.onlineblogging.controller;


import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.CommentDto;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineblogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineblogging.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*", maxAge=3600)
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }


    @PostMapping("/create")
    public ResponseModel<CommentDto> createComment(@RequestParam Integer postId, @RequestBody CommentDto commentDto){

        try{
            return commentService.createComment(postId,commentDto);
        }catch(Exception e){
            return new ResponseModel<>(ResponseStatusEnum.NOT_ACCEPTABLE.getCode(), ResponseStatusEnum.NOT_ACCEPTABLE.getMessage(), false, ResponseMessageEnum.NOT_ACCEPTABLE, null);
        }
    }


    @GetMapping("/by")
    public ResponseModel<List<CommentDto>> getCommentByPostId(@RequestParam Integer postId){
        try{
            return commentService.getCommentByPostId(postId);

        }catch(Exception e){
            return new ResponseModel<>(ResponseStatusEnum.NOT_ACCEPTABLE.getCode(), ResponseStatusEnum.NOT_ACCEPTABLE.getMessage(), false, ResponseMessageEnum.NOT_ACCEPTABLE, null);
        }
    }


}
