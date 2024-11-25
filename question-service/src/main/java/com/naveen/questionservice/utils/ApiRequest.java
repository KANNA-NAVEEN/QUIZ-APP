package com.naveen.questionservice.utils;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequest {
    private String timestamp;
    private String method;
    private String url;
    private Map<String, String[]> queryParams;
    private Object body;

}
