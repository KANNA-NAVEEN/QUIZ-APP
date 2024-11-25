package com.naveen.questionservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ApiResponse {
	private ApiRequest request;
    private String message;
    private String statusCode;

}
