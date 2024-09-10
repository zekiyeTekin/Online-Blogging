package com.zekiyetekin.onlineBlogging.service.implementation;

import com.zekiyetekin.onlineBlogging.entity.Comment;
import com.zekiyetekin.onlineBlogging.entity.Post;
import com.zekiyetekin.onlineBlogging.repository.CommentRepository;
import com.zekiyetekin.onlineBlogging.repository.PostRepository;
import com.zekiyetekin.onlineBlogging.service.CommentService;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    public ResponseEntity<Comment> createComment(Integer postId, Comment comment){

        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Comment newComment = new Comment();

            newComment.setPost(optionalPost.get());
            newComment.setContent(comment.getContent());
            newComment.setPostedBy(comment.getPostedBy());
            newComment.setCreatedAt(new Date());

            return new ResponseEntity<>(commentRepository.save(newComment), HttpStatus.OK);
        }else {
            throw new EntityNotFoundException("Post not found");
        }
    }


    public ResponseEntity<List<Comment>> getCommentByPostId(Integer postId){
        try {
            List<Comment> commentList = commentRepository.findCommentsByPostId(postId);
            if (!commentList.isEmpty()) {
                return new ResponseEntity<>(commentList, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
