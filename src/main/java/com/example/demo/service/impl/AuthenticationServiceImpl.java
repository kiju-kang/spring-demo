package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.example.demo.entity.User;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String token) {
        try {
            // authorization으로부터 type과 credential을 분리
            String[] split = token.split(" ");
            String type = split[0];
            String credential = split[1];

            if ("Basic".equalsIgnoreCase(type)) {
                // credential을 디코딩하여 username과 password를 분리
                String decoded = new String(Base64Utils.decodeFromString(credential));
                String[] usernameAndPassword = decoded.split(":");

                User user = userRepository.findByUsernameAndPassword(usernameAndPassword[0], usernameAndPassword[1]);
                if (user == null)
                    throw new UnauthorizedException("Invalid credentials");
                else
                    return user;

            } else {
                throw new UnauthorizedException("Unsupported type: " + type);

            }

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            throw new UnauthorizedException("Invalid credentials");
        }
    }
}
