package com.zekiyetekin.onlineblogging.repository;

import com.zekiyetekin.onlineblogging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMail(String mail);

    User findUserIdByMail(String mail);

    List<User> findByMailIn(List<String> emails);
}
