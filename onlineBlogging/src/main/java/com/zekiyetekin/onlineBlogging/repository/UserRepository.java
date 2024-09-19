package com.zekiyetekin.onlineBlogging.repository;

import com.zekiyetekin.onlineBlogging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMail(String mail);

    List<User> findByMailIn(List<String> emails);
}
