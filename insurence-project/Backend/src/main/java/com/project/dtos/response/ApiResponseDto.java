package com.project.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private T dataT;

    public ApiResponseDto(int statusCode,String message,T dataT){
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.dataT = dataT;
    }
}