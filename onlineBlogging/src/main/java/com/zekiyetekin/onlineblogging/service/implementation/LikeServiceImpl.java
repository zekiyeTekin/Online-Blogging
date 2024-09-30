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
import com.zekiyetekin.onlineblogging.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository,
                           PostRepository postRepository, LikeMapper likeMapper){
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.likeMapper = likeMapper;
    }

    public ResponseModel<LikeDto> likesPost(Integer userId, Integer postId){

        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found" ));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Like> optionalLike = likeRepository.findLikeByUserAndPost(user,post);

        if (optionalLike.isEmpty()) {

            Like newLike = new Like();
            newLike.setPost(post);
            newLike.setUser(user);
            likeRepository.save(newLike);

            post.setLikeCount(post.getLikeCount() + 1);
            postRepository.save(post);

            return new ResponseModel<>(ResponseStatusEnum.CREATED.getCode(),
                    ResponseStatusEnum.CREATED.getMessage(),
                    true,
                    ResponseMessageEnum.LIKED_SUCCESSFULLY,
                    likeMapper.toDto(newLike));
        } else {
            likeRepository.delete(optionalLike.get());

            post.setLikeCount(post.getLikeCount() - 1);
            postRepository.save(post);
            return new ResponseModel<>(ResponseStatusEnum.CREATED.getCode(),
                    ResponseStatusEnum.CREATED.getMessage(),
                    true,
                    ResponseMessageEnum.DISLIKED_SUCCESSFULLY,
                    likeMapper.toDto(optionalLike.get()));
        }
    }





}
