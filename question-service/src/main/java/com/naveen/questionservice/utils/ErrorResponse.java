package com.naveen.questionservice.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse extends ApiResponse {
	public ErrorResponse(String details2, ApiRequest requestInfo, String message, String statusCode) {
		super(requestInfo,message,statusCode);
	}

	private String details;
}
