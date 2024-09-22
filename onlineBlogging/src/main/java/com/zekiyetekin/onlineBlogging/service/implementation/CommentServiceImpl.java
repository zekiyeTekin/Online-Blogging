package com.zekiyetekin.onlineBlogging.service.implementation;

import com.zekiyetekin.onlineBlogging.common.ResponseModel;
import com.zekiyetekin.onlineBlogging.dto.CommentDto;
import com.zekiyetekin.onlineBlogging.entity.Comment;
import com.zekiyetekin.onlineBlogging.entity.Post;
import com.zekiyetekin.onlineBlogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineBlogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineBlogging.mapper.CommentMapper;
import com.zekiyetekin.onlineBlogging.repository.CommentRepository;
import com.zekiyetekin.onlineBlogging.repository.PostRepository;
import com.zekiyetekin.onlineBlogging.service.CommentService;
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
