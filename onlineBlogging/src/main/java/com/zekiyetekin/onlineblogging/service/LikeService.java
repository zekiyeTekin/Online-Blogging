package com.zekiyetekin.onlineblogging.service;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.entity.Like;
import org.springframework.security.core.userdetails.UserDetails;

public interface LikeService {

    ResponseModel<Like> likesPost(Integer userId, Integer postId);
    ResponseModel<Like> dislikePost(Integer userId, Integer postId);
}
