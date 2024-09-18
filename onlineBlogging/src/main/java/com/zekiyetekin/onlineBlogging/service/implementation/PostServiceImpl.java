package com.zekiyetekin.onlineBlogging.service.implementation;

import com.zekiyetekin.onlineBlogging.common.ResponseModel;
import com.zekiyetekin.onlineBlogging.controller.FileUploadController;
import com.zekiyetekin.onlineBlogging.entity.Post;
import com.zekiyetekin.onlineBlogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineBlogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineBlogging.repository.PostRepository;
import com.zekiyetekin.onlineBlogging.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public ResponseModel<List<Post>> getAllPost() {
        try {
            List<Post> postList = postRepository.findAll();
            if (!postList.isEmpty()) {
                return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, postList);
            }
        } catch (Exception e) {
            return new ResponseModel<>(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseStatusEnum.INTERNAL_SERVER_ERROR.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.NO_CONTENT.getCode(), ResponseStatusEnum.NO_CONTENT.getMessage(), true, ResponseMessageEnum.DATA_NOT_FOUND, new ArrayList<>());
    }


    public ResponseModel<Post> savePost(Post post) {
        try{
            post.setLikeCount(0);
            post.setViewCount(0);
            post.setDate(new Date());

            return new ResponseModel<>(ResponseStatusEnum.CREATED.getCode(), ResponseStatusEnum.CREATED.getMessage(), true, ResponseMessageEnum.CREATED_SUCCESSFULLY, postRepository.save(post));
        }catch (Exception e){
            return new ResponseModel<>(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseStatusEnum.INTERNAL_SERVER_ERROR.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
    }




    public ResponseModel<Post> getPostById(Integer id) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setViewCount(post.getViewCount() + 1);
            return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, postRepository.save(post));
        } else {
            throw new EntityNotFoundException("Post not found");
        }
    }


    public ResponseModel<Post> likePost(Integer id) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setLikeCount(post.getLikeCount() + 1);
            return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.SUCCESSFULLY_DONE, postRepository.save(post));
        } else {
            throw new EntityNotFoundException("Post not found with id" + id);
        }
    }


    public ResponseModel<List<Post>> searchByName(String name) {
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.SEARCHED_SUCCESSFULLY, postRepository.findAllByNameContaining(name));
    }



}
