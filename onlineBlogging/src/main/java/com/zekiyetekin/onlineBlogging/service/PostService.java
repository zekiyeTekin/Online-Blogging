package com.zekiyetekin.onlineBlogging.service;

import com.zekiyetekin.onlineBlogging.common.ResponseModel;
import com.zekiyetekin.onlineBlogging.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    ResponseModel<List<Post>> getAllPost();

    ResponseModel<Post> savePost(Post post);

    ResponseModel<Post> getPostById(Integer id);

    ResponseModel<Post> likePost(Integer id);

    ResponseModel<List<Post>> searchByName(String name);


}
