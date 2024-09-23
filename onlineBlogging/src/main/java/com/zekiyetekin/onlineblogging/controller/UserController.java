package com.zekiyetekin.onlineblogging.controller;

import com.zekiyetekin.onlineblogging.common.ResponseModel;
import com.zekiyetekin.onlineblogging.entity.User;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import com.zekiyetekin.onlineblogging.enumuration.ResponseStatusEnum;
import com.zekiyetekin.onlineblogging.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge=3600)
public class UserController {


    private final UserRepository userRepository;

    @GetMapping("/usernames")
    public ResponseEntity<Map<String, String>> getUsernamesByEmails(@RequestParam List<String> emails) {
        List<User> users = userRepository.findByMailIn(emails);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, String> emailToNameMap = users.stream()
                .collect(Collectors.toMap(User::getMail, User::getName));

        return ResponseEntity.ok(emailToNameMap);
    }

    @GetMapping("/by")
    public ResponseEntity<User> getUserByMail(@RequestParam String mail){

        User user = userRepository.findUserIdByMail(mail);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(user);
    }


}
