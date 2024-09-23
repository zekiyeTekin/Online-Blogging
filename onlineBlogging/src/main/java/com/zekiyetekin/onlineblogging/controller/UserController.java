package com.zekiyetekin.onlineblogging.controller;

import com.zekiyetekin.onlineblogging.entity.User;
import com.zekiyetekin.onlineblogging.repository.UserRepository;
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


}
