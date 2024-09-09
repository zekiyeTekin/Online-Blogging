package com.zekiyetekin.onlineBlogging.service;

import com.zekiyetekin.onlineBlogging.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    ResponseEntity<List<Post>> getAllPost();


}
