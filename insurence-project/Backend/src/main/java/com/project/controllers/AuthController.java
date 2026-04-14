package com.project.controllers;

import com.project.dtos.request.LoginRequestDto;
import com.project.dtos.request.RegisterRequestDto;
import com.project.dtos.response.ApiResponseDto;
import com.project.dtos.response.UserResponseDto;
import com.project.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> register(@RequestBody RegisterRequestDto registerRequestDto) {
        UserResponseDto user = authService.register(registerRequestDto);
        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>(200, "User registered successfully", user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto){
        UserResponseDto user = authService.login(loginRequestDto);
        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>(200, "User logged in successfully", user);

        return ResponseEntity.ok(response);
    }
}