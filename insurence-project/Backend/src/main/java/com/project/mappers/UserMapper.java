package com.project.mappers;

import com.project.dtos.request.UserRegisterRequestDto;
import com.project.dtos.response.UserResponseDto;
import com.project.entities.User;
import com.project.enums.Role;

public class UserMapper {
    public User userRequestDtoToUser(UserRegisterRequestDto userRegisterRequestDto){
        User user = new User();

        user.setName(userRegisterRequestDto.getName());
        if(userRegisterRequestDto.getRole().equals("USER")){
            user.setRole(Role.USER);
        } else if (userRegisterRequestDto.getRole().equals("ADMIN")) {
            user.setRole(Role.ADMIN);
        }
        user.setEmail(userRegisterRequestDto.getEmail());
        user.setPassword(userRegisterRequestDto.getPassword());

        return user;
    }

    public UserResponseDto UsertoUserResponseDto(User user){
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
