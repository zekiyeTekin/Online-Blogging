package com.zekiyetekin.onlineblogging.service;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.PostDto;
import com.zekiyetekin.onlineblogging.entity.Post;

import java.util.List;

public interface PostService {

    ResponseModel<List<PostDto>> getAllPost();

    ResponseModel<PostDto> savePost(Post post);

    ResponseModel<PostDto> getPostById(Integer id);

    ResponseModel<List<PostDto>> searchByName(String name);



}
