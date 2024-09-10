package com.zekiyetekin.onlineBlogging.service;

import com.zekiyetekin.onlineBlogging.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    ResponseEntity<List<Post>> getAllPost();

    Post savePost(Post post);

    ResponseEntity<Post> getPostById(Integer id);

    ResponseEntity<Post> likePost(Integer id);

    ResponseEntity<List<Post>> searchByName(String name);


}
