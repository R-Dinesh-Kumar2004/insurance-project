package com.project.mappers;

import com.project.dtos.request.RegisterRequestDto;
import com.project.dtos.response.UserResponseDto;
import com.project.entities.User;
import com.project.enums.Role;

public class UserMapper {
    public static User toUser(RegisterRequestDto registerRequestDto){
        User user = new User();

        user.setName(registerRequestDto.getName());
        if(registerRequestDto.getRole().equals("USER")){
            user.setRole(Role.USER);
        } else if (registerRequestDto.getRole().equals("ADMIN")) {
            user.setRole(Role.ADMIN);
        }
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(registerRequestDto.getPassword());

        return user;
    }

    public static UserResponseDto toUserResponseDto(User user){
        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        switch (user.getRole()){
            case USER -> dto.setRole("USER");
            case ADMIN -> dto.setRole("ADMIN");
        }

        return dto;
    }
}
