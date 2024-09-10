package com.zekiyetekin.onlineBlogging.service;

import com.zekiyetekin.onlineBlogging.entity.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    ResponseEntity<Comment> createComment(Integer postId, Comment comment);

    ResponseEntity<List<Comment>> getCommentByPostId(Integer postId);
}
