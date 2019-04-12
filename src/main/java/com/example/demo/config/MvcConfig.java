package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.interceptor.AuthenticationInterceptor;

@Configuration

public class MvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
    private AuthenticationInterceptor authenticationInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
            // path가 /user/me인 요청에 대해서만 적용
            .addPathPatterns("/users/me");
    }
}
