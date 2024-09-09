package com.zekiyetekin.onlineBlogging.controller;

import com.zekiyetekin.onlineBlogging.entity.Post;
import com.zekiyetekin.onlineBlogging.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {


    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/get/posts")
    public ResponseEntity<List<Post>> getAllPost(){
        return postService.getAllPost();

    }


}
