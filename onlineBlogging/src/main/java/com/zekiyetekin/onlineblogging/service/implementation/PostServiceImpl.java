package com.zekiyetekin.onlineblogging.service.implementation;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.PostDto;
import com.zekiyetekin.onlineblogging.entity.Post;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineblogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineblogging.mapper.PostMapper;
import com.zekiyetekin.onlineblogging.repository.PostRepository;
import com.zekiyetekin.onlineblogging.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    public ResponseModel<List<PostDto>> getAllPost() {
        try {
            List<Post> postList = postRepository.findAll();
            if (!postList.isEmpty()) {
                return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, postMapper.convertList(postList));
            }
        } catch (Exception e) {
            return new ResponseModel<>(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseStatusEnum.INTERNAL_SERVER_ERROR.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.NO_CONTENT.getCode(), ResponseStatusEnum.NO_CONTENT.getMessage(), true, ResponseMessageEnum.DATA_NOT_FOUND, new ArrayList<>());
    }


    public ResponseModel<PostDto> savePost(Post post) {
        try{
            post.setLikeCount(0);
            post.setViewCount(0);
            post.setDate(new Date());

            return new ResponseModel<>(ResponseStatusEnum.CREATED.getCode(), ResponseStatusEnum.CREATED.getMessage(), true, ResponseMessageEnum.CREATED_SUCCESSFULLY, postMapper.toDto(postRepository.save(post)));
        }catch (Exception e){
            return new ResponseModel<>(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseStatusEnum.INTERNAL_SERVER_ERROR.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
    }



    public ResponseModel<PostDto> getPostById(Integer id) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setViewCount(post.getViewCount() + 1);
            return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, postMapper.toDto(postRepository.save(post)));
        } else {
            throw new EntityNotFoundException("Post not found");
        }
    }


    public ResponseModel<List<PostDto>> searchByName(String name) {
        try{
            List<Post> searchedPost = postRepository.findAllByNameContaining(name);
            if (!searchedPost.isEmpty()) {
                return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.SEARCHED_SUCCESSFULLY, postMapper.convertList(searchedPost));
            }
            return new ResponseModel<>(ResponseStatusEnum.NO_CONTENT.getCode(), ResponseStatusEnum.NO_CONTENT.getMessage(), true, ResponseMessageEnum.DATA_NOT_FOUND, new ArrayList<>());
        }catch (Exception e){
            return new ResponseModel<>(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseStatusEnum.INTERNAL_SERVER_ERROR.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, new ArrayList<>());
        }
    }



}
