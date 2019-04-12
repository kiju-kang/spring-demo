package com.example.demo.exception;
//인증이 필요한 경우, 혹은 잘못된 인증

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")

public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
	    super(message);
	  }
}