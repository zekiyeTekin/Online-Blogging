package com.zekiyetekin.onlineblogging.mapper;


import com.zekiyetekin.onlineblogging.dto.PostDto;
import com.zekiyetekin.onlineblogging.entity.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    public PostDto toDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .name(post.getName())
                .content(post.getContent())
                .postedBy(post.getPostedBy())
                .img(post.getImg())
                .date(post.getDate())
                .likeCount(post.getLikeCount())
                .viewCount(post.getViewCount())
                .tags(post.getTags())
                .build();
    }

    public List<PostDto> convertList(List<Post> postList){
        List<PostDto> postDtoList = new ArrayList<>();

        for(Post post : postList){
            postDtoList.add(toDto(post));
        }
        return postDtoList;
    }


}
