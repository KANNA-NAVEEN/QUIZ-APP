package com.naveen.questionservice.utils;

import jakarta.servlet.http.HttpServletRequest;

public class ResponseBuilder {
	private ResponseBuilder() {
		
	}
	public static SuccessResponse buildSuccessResponse(String statusCode,String message, Object data, HttpServletRequest request) {
        ApiRequest requestInfo = new ApiRequest();
        requestInfo.setMethod(request.getMethod());
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setQueryParams(request.getParameterMap());
        return new SuccessResponse(data,requestInfo,message,statusCode);
    }

    public static ErrorResponse buildErrorResponse(String statusCode, String message, String details, HttpServletRequest request) {
    	ApiRequest requestInfo = new ApiRequest();
        requestInfo.setMethod(request.getMethod());
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setQueryParams(request.getParameterMap());

        return new ErrorResponse(details,requestInfo,message,statusCode);
    }
}
