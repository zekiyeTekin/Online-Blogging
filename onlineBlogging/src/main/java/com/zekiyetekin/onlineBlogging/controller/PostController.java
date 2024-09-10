package com.zekiyetekin.onlineBlogging.controller;

import com.zekiyetekin.onlineBlogging.entity.Post;
import com.zekiyetekin.onlineBlogging.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        try{
            //System.out.println("Received Post: " + post);
            Post createdPost = postService.savePost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/by")
    public ResponseEntity<Post> getPostById(@RequestParam Integer id) {

        try {
            return postService.getPostById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("like/by")
    public ResponseEntity<Post> likePost(@RequestParam Integer id){

        try{
            return postService.likePost(id);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search/by")
    public ResponseEntity<List<Post>> searchByName(@RequestParam String name){

        try{
            return postService.searchByName(name);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
