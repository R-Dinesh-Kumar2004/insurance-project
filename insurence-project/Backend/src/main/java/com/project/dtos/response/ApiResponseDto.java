package com.project.dtos.response;


import java.time.LocalDateTime;

public class ApiResponseDto<T> {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private T dataT;

    public ApiResponseDto(){}

    public ApiResponseDto(int statusCode,String message,T dataT){
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.dataT = dataT;
    }
}