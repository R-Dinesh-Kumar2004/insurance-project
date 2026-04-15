package com.project.services.impl;

import com.project.dtos.request.LoginRequestDto;
import com.project.dtos.request.RegisterRequestDto;
import com.project.dtos.response.UserResponseDto;
import com.project.entities.User;
import com.project.exceptions.InvalidUsernameOrPasswordException;
import com.project.exceptions.UserAlreadyExistException;
import com.project.mappers.UserMapper;
import com.project.repositories.UserRepository;
import com.project.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(RegisterRequestDto registerRequestDto) {
        String email = registerRequestDto.getEmail();

        User user = userRepository.findByEmail(email);
        if(user != null) {
            throw new UserAlreadyExistException("User with email " + email + " already exists");
        }

        User newUser = UserMapper.toUser(registerRequestDto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);

        return UserMapper.toUserResponseDto(newUser);
    }

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();

        User user = userRepository.findByEmail(email);
        if(user != null) {
            throw new InvalidUsernameOrPasswordException("User not found");
        }

        if(user.equals(loginRequestDto.getPassword())){
            return UserMapper.toUserResponseDto(user);
        } else {
            throw new InvalidUsernameOrPasswordException("Email or password is incorrect");
        }
    }
}
