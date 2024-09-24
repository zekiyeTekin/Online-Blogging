package com.zekiyetekin.onlineblogging.service;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.entity.Like;

public interface LikeService {

    ResponseModel<Like> likesPost(Integer userId, Integer postId);
}
