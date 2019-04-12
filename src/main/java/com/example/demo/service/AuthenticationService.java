package com.example.demo.service;

import com.example.demo.entity.User;

public interface AuthenticationService {

	User authenticate(String token);
}
