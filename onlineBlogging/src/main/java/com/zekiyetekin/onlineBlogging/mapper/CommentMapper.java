package com.zekiyetekin.onlineBlogging.mapper;

import com.zekiyetekin.onlineBlogging.dto.CommentDto;
import com.zekiyetekin.onlineBlogging.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {

    private final PostMapper postMapper;

    public CommentMapper(PostMapper postMapper){
        this.postMapper = postMapper;
    }

    public CommentDto toDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .postedBy(comment.getPostedBy())
                .post(postMapper.toDto(comment.getPost()))
                .build();
    }

    public List<CommentDto> convertList(List<Comment> commentList){
        List<CommentDto> commentDtoList = new ArrayList<>();

        for(Comment comment : commentList){
            commentDtoList.add(toDto(comment));
        }
        return commentDtoList;
    }
}
