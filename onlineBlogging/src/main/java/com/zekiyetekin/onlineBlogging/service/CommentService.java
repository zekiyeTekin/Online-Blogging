package com.zekiyetekin.onlineBlogging.service;

import com.zekiyetekin.onlineBlogging.common.ResponseModel;
import com.zekiyetekin.onlineBlogging.entity.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    ResponseModel<Comment> createComment(Integer postId, Comment comment);

    ResponseModel<List<Comment>> getCommentByPostId(Integer postId);
}
