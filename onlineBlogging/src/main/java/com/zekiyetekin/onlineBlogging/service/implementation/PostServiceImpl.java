package com.zekiyetekin.onlineBlogging.service.implementation;

import com.zekiyetekin.onlineBlogging.entity.Post;
import com.zekiyetekin.onlineBlogging.repository.PostRepository;
import com.zekiyetekin.onlineBlogging.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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


    public Post savePost(Post post) {
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setDate(new Date());

        return postRepository.save(post);
    }


    public ResponseEntity<Post> getPostById(Integer id) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setViewCount(post.getViewCount() + 1);
            return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Post not found");
        }
    }


    public ResponseEntity<Post> likePost(Integer id) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setLikeCount(post.getLikeCount() + 1);
            return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Post not found with id" + id);
        }
    }


    public ResponseEntity<List<Post>> searchByName(String name) {
        return new ResponseEntity<>(postRepository.findAllByNameContaining(name), HttpStatus.OK);
    }

}
