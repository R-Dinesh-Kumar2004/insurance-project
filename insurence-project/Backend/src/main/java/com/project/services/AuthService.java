package com.project.services;

import com.project.dtos.request.LoginRequestDto;
import com.project.dtos.request.RegisterRequestDto;
import com.project.dtos.response.UserResponseDto;

public interface AuthService {
    public UserResponseDto register(RegisterRequestDto registerRequestDto);
    public UserResponseDto login(LoginRequestDto loginRequestDto);
}
