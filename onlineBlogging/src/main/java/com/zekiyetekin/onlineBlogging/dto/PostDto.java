package com.zekiyetekin.onlineBlogging.dto;



import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Integer id;
    private String name;
    private String content;
    private String postedBy;
    private String img;
    private Date date;
    private Integer likeCount;
    private Integer viewCount;
    private List<String> tags;
}
