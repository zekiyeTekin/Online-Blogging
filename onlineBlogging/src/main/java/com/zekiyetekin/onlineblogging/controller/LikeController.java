package com.zekiyetekin.onlineblogging.controller;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.dto.LikeDto;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineblogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineblogging.service.LikeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@CrossOrigin(origins = "*", maxAge=3600)
public class LikeController {

    private final LikeService likeService;
    @Autowired
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }


    @PostMapping("/by")
    public ResponseModel<LikeDto> likePost(@RequestParam Integer userId, @RequestParam Integer postId){

        try {
            return likeService.likesPost(userId,postId);
        } catch (EntityNotFoundException e) {
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
    }



}
