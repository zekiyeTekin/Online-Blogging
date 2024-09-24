package com.zekiyetekin.onlineblogging.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDto {



    private PostDto post;

    private UserDto user;
}
