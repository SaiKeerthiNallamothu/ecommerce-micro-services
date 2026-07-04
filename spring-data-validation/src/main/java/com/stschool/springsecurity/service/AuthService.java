package com.stschool.springsecurity.service;

import com.stschool.springsecurity.dto.AuthResponseDto;
import com.stschool.springsecurity.dto.LoginRequestDto;
import com.stschool.springsecurity.dto.SignupRequestDto;
import com.stschool.springsecurity.dto.UserResponseDto;
import com.stschool.springsecurity.exception.IllegalCredentialsException;
import com.stschool.springsecurity.exception.UserAlreadyExistException;
import com.stschool.springsecurity.exception.UserNotFoundException;

public interface AuthService {
   UserResponseDto signup(SignupRequestDto signupRequestDto)throws UserAlreadyExistException;
   AuthResponseDto login(LoginRequestDto loginRequestDto)throws IllegalCredentialsException, UserNotFoundException;
}
