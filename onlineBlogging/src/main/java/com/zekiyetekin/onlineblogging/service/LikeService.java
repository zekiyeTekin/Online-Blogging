package com.zekiyetekin.onlineblogging.service;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.LikeDto;

public interface LikeService {

    ResponseModel<LikeDto> likesPost(Integer userId, Integer postId);
}
