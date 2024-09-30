package com.zekiyetekin.onlineblogging.service.implementation;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.LikeDto;
import com.zekiyetekin.onlineblogging.entity.Like;
import com.zekiyetekin.onlineblogging.entity.Post;
import com.zekiyetekin.onlineblogging.entity.User;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineblogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineblogging.mapper.LikeMapper;
import com.zekiyetekin.onlineblogging.repository.LikeRepository;
import com.zekiyetekin.onlineblogging.repository.PostRepository;
import com.zekiyetekin.onlineblogging.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LikeServiceImplTest {

    @InjectMocks
    private LikeServiceImpl likeService;
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private LikeMapper likeMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostRepository postRepository;

    private User user;
    private Post post;
    private Like like;
    private LikeDto likeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
        user.setName("Test User");

        post = new Post();
        post.setId(1);
        post.setLikeCount(1);

        like = new Like();
        like.setPost(post);
        like.setUser(user);

        likeDto = new LikeDto();
    }

    @Test
    void testLikesPost_Success() {
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(likeRepository.findLikeByUserAndPost(user, post)).thenReturn(Optional.empty());
        when(likeMapper.toDto(any(Like.class))).thenReturn(likeDto);

        ResponseModel<LikeDto> response = likeService.likesPost(1, 1);

        verify(likeRepository).save(any(Like.class));
        verify(postRepository).save(post);
        assertEquals(ResponseStatusEnum.CREATED.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.LIKED_SUCCESSFULLY, response.getMessage());
        assertEquals(2, post.getLikeCount());
    }

    @Test
    void testLikePost_Dislikes(){
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(likeRepository.findLikeByUserAndPost(user, post)).thenReturn(Optional.of(like));
        when(likeMapper.toDto(any(Like.class))).thenReturn(likeDto);

        ResponseModel<LikeDto> response = likeService.likesPost(1, 1);

        verify(likeRepository).delete(like);
        verify(postRepository).save(post);

        assertEquals(ResponseStatusEnum.CREATED.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.DISLIKED_SUCCESSFULLY, response.getMessage());
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void testLikePost_PostNotFound(){
        when(postRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            likeService.likesPost(1,1);
        });

        assertEquals("Post not found", exception.getMessage());
    }

    @Test
    void testLikePost_UserNotFound(){
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        when(postRepository.findById(1)).thenReturn(Optional.of(post));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            likeService.likesPost(1,1);
        });

        assertEquals("User not found", exception.getMessage());
    }

}