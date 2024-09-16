package com.zekiyetekin.onlineBlogging.controller;

import com.zekiyetekin.onlineBlogging.entity.AuthenticationResponse;
import com.zekiyetekin.onlineBlogging.entity.User;
import com.zekiyetekin.onlineBlogging.service.implementation.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private  final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authenticationService.authenticationResponse(request));
    }
}
