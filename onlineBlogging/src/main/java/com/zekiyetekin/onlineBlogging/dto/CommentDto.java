package com.zekiyetekin.onlineBlogging.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private String content;
    private Date createdAt;
    private String postedBy;
    private PostDto post;
}
