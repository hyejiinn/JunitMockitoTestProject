package org.example.test.spring.ctl;

import org.example.test.spring.ctl.res.ApiResponse;
import org.example.test.spring.exception.TestUserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice
{
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TestUserNotFound.class)
	public ApiResponse<Object> handleTestUserNotFoundEx(TestUserNotFound e)
	{
		log.error("ApiControllerAdvice.handleTestUserNotFoundEx : {} ", e.getMessage(), e);
		return ApiResponse.of(HttpStatus.BAD_REQUEST, null);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ApiResponse<Object> handleException(Exception e)
	{
		log.error("ApiControllerAdvice.handleException : {} ", e.getMessage(), e);
		return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
}
