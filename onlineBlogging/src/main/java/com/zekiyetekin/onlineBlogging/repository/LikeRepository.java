package com.zekiyetekin.onlineBlogging.repository;

import com.zekiyetekin.onlineBlogging.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
}
