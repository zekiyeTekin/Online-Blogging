package com.zekiyetekin.onlineblogging.service;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.CommentDto;

import java.util.List;

public interface CommentService {

    ResponseModel<CommentDto> createComment(Integer postId, CommentDto commentDto);

    ResponseModel<List<CommentDto>> getCommentByPostId(Integer postId);
}
