package com.zekiyetekin.onlineBlogging.service;

import com.zekiyetekin.onlineBlogging.common.ResponseModel;
import com.zekiyetekin.onlineBlogging.dto.PostDto;
import com.zekiyetekin.onlineBlogging.entity.Post;

import java.util.List;

public interface PostService {

    ResponseModel<List<PostDto>> getAllPost();

    ResponseModel<PostDto> savePost(Post post);

    ResponseModel<PostDto> getPostById(Integer id);

    ResponseModel<PostDto> likePost(Integer id);

    ResponseModel<List<PostDto>> searchByName(String name);



}
