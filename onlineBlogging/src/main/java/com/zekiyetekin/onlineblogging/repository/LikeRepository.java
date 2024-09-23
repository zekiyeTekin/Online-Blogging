package com.zekiyetekin.onlineblogging.repository;

import com.zekiyetekin.onlineblogging.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
}
