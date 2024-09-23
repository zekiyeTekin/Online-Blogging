package com.zekiyetekin.onlineblogging.controller;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.PostDto;
import com.zekiyetekin.onlineblogging.entity.Post;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineblogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineblogging.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*", maxAge=3600)
public class PostController {


    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/get/posts")
    public ResponseModel<List<PostDto>> getAllPost(){
        return postService.getAllPost();
    }


    @PostMapping("/create")
    public ResponseModel<PostDto> createPost(@RequestBody Post post){
        return postService.savePost(post);
    }

    @GetMapping("/by")
    public ResponseModel<PostDto> getPostById(@RequestParam Integer id) {

        try {
            return postService.getPostById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
    }

    @PutMapping("like/by")
    public ResponseModel<PostDto> likePost(@RequestParam Integer id){

        try{
            return postService.likePost(id);
        }catch (EntityNotFoundException e) {
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
    }

    @GetMapping("/search/by")
    public ResponseModel<List<PostDto>> searchByName(@RequestParam String name){

        try{
            return postService.searchByName(name);
        }catch (Exception e){
            return new ResponseModel<>(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseStatusEnum.INTERNAL_SERVER_ERROR.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
    }




}
