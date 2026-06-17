package com.stschool.userservice.service;

import com.stschool.userservice.dto.AuthResponseDto;
import com.stschool.userservice.dto.LoginRequestDto;
import com.stschool.userservice.dto.SignupRequestDto;
import com.stschool.userservice.dto.UserResponseDto;
import com.stschool.userservice.exception.InvalidCredentialsException;
import com.stschool.userservice.exception.UserExistsException;
import com.stschool.userservice.exception.UserNotFoundException;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidCredentialsException;
    UserResponseDto signup(SignupRequestDto signupRequestDto) throws UserExistsException;
}
