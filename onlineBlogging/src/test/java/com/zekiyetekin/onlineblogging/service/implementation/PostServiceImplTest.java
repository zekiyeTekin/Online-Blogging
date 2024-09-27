package com.zekiyetekin.onlineblogging.service.implementation;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.PostDto;
import com.zekiyetekin.onlineblogging.entity.Post;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineblogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineblogging.mapper.PostMapper;
import com.zekiyetekin.onlineblogging.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.*;

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

    @Test
    void testGetPostById_Success(){
        // Arrange:
        Post post = new Post();
        post.setId(1);
        post.setLikeCount(10);
        post.setViewCount(5);

        PostDto postDto = new PostDto();
        postDto.setId(1);
        postDto.setLikeCount(10);
        postDto.setViewCount(6);

        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(post);
        when(postMapper.toDto(post)).thenReturn(postDto);

        // Act:
        ResponseModel<PostDto> response = postService.getPostById(1);

        // Assert:
        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, response.getMessage());
        assertTrue(response.getSuccess());
        assertEquals(postDto, response.getData());

        assertEquals(6, post.getViewCount());
    }

    @Test
    void testGetPostById_Exception(){
        // Arrange:
        when(postRepository.findById(1)).thenReturn(Optional.empty());

        // Act:
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            postService.getPostById(1);
        });

        // Assert:
        assertEquals("Post not found", exception.getMessage());
    }

    @Test
    void testSearchByName_Success(){
        // Arrange
        String searchName = "Test post";

        List<Post> postList = new ArrayList<>();

        Post post1 = new Post();
        post1.setId(1);
        post1.setName("Test post 1");
        postList.add(post1);

        Post post2 = new Post();
        post2.setId(2);
        post2.setName("Test post 2");
        postList.add(post2);

        List<PostDto> postDtoList = new ArrayList<>();

        PostDto postDto1 = new PostDto();
        postDto1.setId(1);
        postDto1.setName("Test post 1");
        postDtoList.add(postDto1);

        PostDto postDto2 = new PostDto();
        postDto2.setId(2);
        postDto2.setName("Test post 2");
        postDtoList.add(postDto2);

        when(postRepository.findAllByNameContaining(searchName)).thenReturn(postList);
        when(postMapper.convertList(postList)).thenReturn(postDtoList);

        // Act:
        ResponseModel<List<PostDto>> response = postService.searchByName(searchName);

        // Assert
        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertTrue(response.getSuccess());
        assertEquals(ResponseMessageEnum.SEARCHED_SUCCESSFULLY, response.getMessage());
        assertEquals(2, response.getData().size());
        assertEquals("Test post 1", response.getData().get(0).getName());
        assertEquals("Test post 2", response.getData().get(1).getName());
    }

    @Test
    void testSearchByName_Exception(){
        // Arrange
        String searchName = "NonExistingPost";

        List<Post> emptyPostList = new ArrayList<>();

        when(postRepository.findAllByNameContaining(searchName)).thenReturn(emptyPostList);

        // Act:
        ResponseModel<List<PostDto>> response = postService.searchByName(searchName);

        // Assert:
        assertEquals(ResponseStatusEnum.NO_CONTENT.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.DATA_NOT_FOUND, response.getMessage());
        assertTrue(response.getSuccess());
        assertTrue(response.getData().isEmpty());

        verify(postRepository, times(1)).findAllByNameContaining(searchName);
        verify(postMapper, never()).convertList(anyList());
    }

}