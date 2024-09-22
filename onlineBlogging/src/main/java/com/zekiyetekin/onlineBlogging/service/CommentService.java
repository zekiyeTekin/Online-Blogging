package com.zekiyetekin.onlineBlogging.service;

import com.zekiyetekin.onlineBlogging.common.ResponseModel;
import com.zekiyetekin.onlineBlogging.dto.CommentDto;
import com.zekiyetekin.onlineBlogging.entity.Comment;

import java.util.List;

public interface CommentService {

    ResponseModel<CommentDto> createComment(Integer postId, CommentDto commentDto);

    ResponseModel<List<CommentDto>> getCommentByPostId(Integer postId);
}
