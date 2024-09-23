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
import com.zekiyetekin.onlineblogging.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, CommentMapper commentMapper){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }


    public ResponseModel<CommentDto> createComment(Integer postId, CommentDto commentDto){

        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Comment newComment = new Comment();

            newComment.setPost(optionalPost.get());
            newComment.setContent(commentDto.getContent());
            newComment.setPostedBy(commentDto.getPostedBy());
            newComment.setCreatedAt(new Date());

            Comment savedComment = commentRepository.save(newComment);

            return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.CREATED_SUCCESSFULLY, commentMapper.toDto(savedComment));
        }else {
            throw new EntityNotFoundException("Post not found");
        }
    }


    public ResponseModel<List<CommentDto>> getCommentByPostId(Integer postId){
        try {
            List<Comment> commentList = commentRepository.findCommentsByPostId(postId);
            if (!commentList.isEmpty()) {
                return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, commentMapper.convertList(commentList));
            }
            return new ResponseModel<>(ResponseStatusEnum.NO_CONTENT.getCode(), ResponseStatusEnum.NO_CONTENT.getMessage(), true, ResponseMessageEnum.SUCCESSFULLY_DONE, new ArrayList<>());
        } catch (Exception e) {
            return new ResponseModel<>(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseStatusEnum.INTERNAL_SERVER_ERROR.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
    }



}
