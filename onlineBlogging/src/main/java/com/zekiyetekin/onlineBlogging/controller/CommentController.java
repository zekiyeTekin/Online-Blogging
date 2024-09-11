package com.zekiyetekin.onlineBlogging.controller;


import com.zekiyetekin.onlineBlogging.common.ResponseModel;
import com.zekiyetekin.onlineBlogging.entity.Comment;
import com.zekiyetekin.onlineBlogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineBlogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineBlogging.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }



    @PostMapping("/create")
    public ResponseModel<Comment> createComment(@RequestParam Integer postId, @RequestBody Comment comment){

        try{
            return commentService.createComment(postId,comment);
        }catch(Exception e){
            return new ResponseModel<>(ResponseStatusEnum.NOT_ACCEPTABLE.getCode(), ResponseStatusEnum.NOT_ACCEPTABLE.getMessage(), false, ResponseMessageEnum.NOT_ACCEPTABLE, null);
        }
    }


    @GetMapping("/by")
    public ResponseModel<List<Comment>> getCommentByPostId(@RequestParam Integer postId){
        try{
            return commentService.getCommentByPostId(postId);

        }catch(Exception e){
            return new ResponseModel<>(ResponseStatusEnum.NOT_ACCEPTABLE.getCode(), ResponseStatusEnum.NOT_ACCEPTABLE.getMessage(), false, ResponseMessageEnum.NOT_ACCEPTABLE, null);
        }
    }


}
