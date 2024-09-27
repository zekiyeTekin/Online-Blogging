package com.zekiyetekin.onlineblogging.service.implementation;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.PostDto;
import com.zekiyetekin.onlineblogging.entity.Post;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineblogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineblogging.mapper.PostMapper;
import com.zekiyetekin.onlineblogging.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostMapper postMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePost_Success() {

        // Arrange:
        Post post = new Post();
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setDate(new Date());

        PostDto postDto = new PostDto();

        when(postRepository.save(any(Post.class))).thenReturn(post);
        when(postMapper.toDto(any(Post.class))).thenReturn(postDto);

        // Act:
        ResponseModel<PostDto> response = postService.savePost(post);

        // Assert:
        assertEquals(ResponseStatusEnum.CREATED.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.CREATED_SUCCESSFULLY, response.getMessage());
        assertTrue(response.getSuccess());
        assertNotNull(response.getData());

        verify(postRepository, times(1)).save(post);
        verify(postMapper, times(1)).toDto(post);
    }

    @Test
    void testSavePost_Exception(){
        // Arrange:
        Post post = new Post();
        when(postRepository.save(any(Post.class))).thenThrow(new RuntimeException("Database error"));

        //Act:
        ResponseModel<PostDto> response = postService.savePost(post);

        //Assert:
        assertEquals(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.DATA_NOT_FOUND, response.getMessage());
        assertFalse(response.getSuccess());
        assertNull(response.getData());

        verify(postRepository, times(1)).save(post);
        verify(postMapper, times(0)).toDto(any(Post.class));
    }

    @Test
    void testGetAllPost_Success(){
        // Arrenge:
        Post post = new Post();
        List<Post> postList = Arrays.asList(post);
        List<PostDto> postDtoList = Arrays.asList(new PostDto());

        when(postRepository.findAll()).thenReturn(postList);
        when(postMapper.convertList(postList)).thenReturn(postDtoList);

        // Act:
        ResponseModel<List<PostDto>> response = postService.getAllPost();

        // Assert:
        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, response.getMessage());
        assertTrue(response.getSuccess());
        assertEquals(postDtoList, response.getData());
    }

    @Test
    void testGetAllPost_Exception(){
        // Arrange:
        when(postRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // Act:
        ResponseModel<List<PostDto>> response = postService.getAllPost();

        // Assert:
        assertEquals(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.DATA_NOT_FOUND, response.getMessage());
        assertFalse(response.getSuccess());
        assertNull(response.getData());
    }
}