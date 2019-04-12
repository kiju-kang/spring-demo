package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

	// 가입
    User join(String username, String password);
    // 인증 & 개인정보 조회
    User authentication(String token);
    // 비밀번호 변경
    User updatePassword(Integer userId, String newPassword);
    // 탈퇴
    void withdraw(Integer userId);
	
}
