package com.zekiyetekin.onlineblogging.repository;

import com.zekiyetekin.onlineblogging.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentsByPostId(Integer postId);

}
