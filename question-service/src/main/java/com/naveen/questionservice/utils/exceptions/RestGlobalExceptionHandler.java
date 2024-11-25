package com.naveen.questionservice.utils.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.naveen.questionservice.utils.ApiResponse;
import com.naveen.questionservice.utils.ResponseBuilder;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestGlobalExceptionHandler {

	public RestGlobalExceptionHandler() {
		// TODO Auto-generated constructor stub
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ApiResponse handleGenericExcepion(Exception exception, HttpServletRequest request) {
		return ResponseBuilder.buildErrorResponse(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(),
				ExceptionUtils.getStackTrace(exception), request);
	}
}
