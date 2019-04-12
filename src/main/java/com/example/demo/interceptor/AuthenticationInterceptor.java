package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.entity.User;
import com.example.demo.service.AuthenticationService;

@Component

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	private final AuthenticationService authenticationService;

	@Autowired
	public AuthenticationInterceptor(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 헤더로부터 토큰을 읽어서
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		// 인증과정을 수행하고
		User user = authenticationService.authenticate(token);
		// 그 결과를 request attribute으로 넘겨준다.
		request.setAttribute("user", user);
		
		return super.preHandle(request, response, handler);
	}
}
