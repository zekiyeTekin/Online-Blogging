package com.zekiyetekin.onlineblogging.repository;

import com.zekiyetekin.onlineblogging.entity.Like;
import com.zekiyetekin.onlineblogging.entity.Post;
import com.zekiyetekin.onlineblogging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    Optional<Like> findLikeByUserAndPost(User user, Post post);


}
