package com.stschool.userservice.service;

import com.stschool.userservice.dto.SignupRequestDto;
import com.stschool.userservice.dto.UserRequestDto;
import com.stschool.userservice.dto.UserResponseDto;
import com.stschool.userservice.dto.UserStatusRequestDto;
import com.stschool.userservice.entity.User;
import com.stschool.userservice.exception.UserExistsException;
import com.stschool.userservice.exception.UserNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    UserResponseDto save(SignupRequestDto signupRequestDto) throws UserExistsException;
    User findByEmail(String email) throws UserNotFoundException;
    UserResponseDto update(UserRequestDto userRequestDto) throws UserNotFoundException;
    List<UserResponseDto> findAll();
    UserResponseDto findById(int id) throws UserNotFoundException;
    void delete(int id) throws UserNotFoundException;
    boolean updateStatus(int id, UserStatusRequestDto userStatusRequestDto) throws UserNotFoundException;
    boolean existsByEmail(String email);
}
