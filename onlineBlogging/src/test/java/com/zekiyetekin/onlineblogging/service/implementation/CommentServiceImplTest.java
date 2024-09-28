package com.zekiyetekin.onlineblogging.service.implementation;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.CommentDto;
import com.zekiyetekin.onlineblogging.entity.Comment;
import com.zekiyetekin.onlineblogging.entity.Post;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineblogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineblogging.mapper.CommentMapper;
import com.zekiyetekin.onlineblogging.repository.CommentRepository;
import com.zekiyetekin.onlineblogging.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentMapper commentMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateComment_Success() {
        // Arrange
        Integer postId = 1;
        Post post = new Post();
        post.setId(postId);

        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Test Comment");
        commentDto.setPostedBy("User1");

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent("Test Comment");
        comment.setPostedBy("User1");
        comment.setCreatedAt(new Date());

        CommentDto savedCommentDto = new CommentDto();
        savedCommentDto.setContent("Test Comment");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        when(commentMapper.toDto(comment)).thenReturn(savedCommentDto);
        // Act:
        ResponseModel<CommentDto> response = commentService.createComment(postId, commentDto);
        // Assert:
        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.CREATED_SUCCESSFULLY, response.getMessage());
        assertTrue(response.getSuccess());
        assertEquals(savedCommentDto, response.getData());

        verify(postRepository, times(1)).findById(postId);
        verify(commentRepository, times(1)).save(any(Comment.class));
        verify(commentMapper, times(1)).toDto(comment);
    }

    @Test
    void testCreateComment_Exception() {
        // Arrange
        Integer postId = 1;
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Test Comment");

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            commentService.createComment(postId, commentDto);
        });

        assertEquals("Post not found", thrown.getMessage());

        verify(postRepository, times(1)).findById(postId);
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    void testGetCommentByPostId(){
        // Arrange:
        Integer postId = 1;
        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment());

        when(commentRepository.findCommentsByPostId(postId)).thenReturn(commentList);
        when(commentMapper.convertList(commentList)).thenReturn(new ArrayList<>());

        // Act:
        ResponseModel<List<CommentDto>> response = commentService.getCommentByPostId(postId);

        // Assert:
        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertTrue(response.getSuccess());

        verify(commentRepository, times(1)).findCommentsByPostId(postId);
        verify(commentMapper,times(1)).convertList(commentList);
    }

    @Test
    void testGetCommentByPostId_Exception(){
        // Arrange:
        Integer postId = 1;
        List<Comment> commentList = new ArrayList<>();

        when(commentRepository.findCommentsByPostId(postId)).thenReturn(commentList);
        // Act:
        ResponseModel<List<CommentDto>> response = commentService.getCommentByPostId(postId);

        // Assert:
        assertEquals(ResponseStatusEnum.NO_CONTENT.getCode(), response.getCode());
        assertTrue(response.getSuccess());


        verify(commentRepository, times(1)).findCommentsByPostId(postId);
        verify(commentMapper, never()).convertList(commentList);
    }



}