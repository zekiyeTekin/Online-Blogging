package com.zekiyetekin.onlineBlogging.service.implementation;

import com.zekiyetekin.onlineBlogging.entity.AuthenticationResponse;
import com.zekiyetekin.onlineBlogging.entity.User;
import com.zekiyetekin.onlineBlogging.enumuration.RoleEnum;
import com.zekiyetekin.onlineBlogging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;


    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setName(request.getName());
        user.setMail(request.getMail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(RoleEnum.USER);

        user = userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse authenticationResponse(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByMail(request.getUsername()).orElseThrow();
        String token  = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}
