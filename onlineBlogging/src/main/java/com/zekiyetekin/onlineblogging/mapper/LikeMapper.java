package com.zekiyetekin.onlineblogging.mapper;

import com.zekiyetekin.onlineblogging.dto.LikeDto;
import com.zekiyetekin.onlineblogging.entity.Like;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    private final UserMapper userMapper;
    private final PostMapper postMapper;

    public LikeMapper(UserMapper userMapper, PostMapper postMapper){
        this.userMapper = userMapper;
        this.postMapper = postMapper;
    }


    public LikeDto toDto(Like like){
        return LikeDto.builder()
                .user(userMapper.toDto(like.getUser()))
                .post(postMapper.toDto(like.getPost()))
                .build();
    }

}
