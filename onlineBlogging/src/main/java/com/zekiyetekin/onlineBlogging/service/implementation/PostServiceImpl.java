package com.zekiyetekin.onlineBlogging.service.implementation;

import com.zekiyetekin.onlineBlogging.entity.Post;
import com.zekiyetekin.onlineBlogging.repository.PostRepository;
import com.zekiyetekin.onlineBlogging.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public ResponseEntity<List<Post>> getAllPost() {
        try {
            List<Post> postList = postRepository.findAll();
            if (!postList.isEmpty()) {
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
    }
}
