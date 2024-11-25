package com.naveen.questionservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuccessResponse extends ApiResponse {
	public SuccessResponse(Object data, ApiRequest requestInfo, String message, String statusCode) {
		super(requestInfo,message,statusCode);
	}

	private Object data;
}
