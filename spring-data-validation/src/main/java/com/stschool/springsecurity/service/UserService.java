package com.stschool.springsecurity.service;

import com.stschool.springsecurity.dto.UserResponseDto;
import com.stschool.springsecurity.entity.User;
import com.stschool.springsecurity.exception.UserAlreadyExistException;
import com.stschool.springsecurity.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserResponseDto save(User user) throws UserAlreadyExistException;
    List<UserResponseDto> getAll();
    UserResponseDto findByEmail(String email) throws  UserNotFoundException;
    UserResponseDto update(String email,User user) throws UserNotFoundException;
    void deleteUser(String email) throws UserNotFoundException;
    boolean existsByEmail(String email) throws UserNotFoundException;
}
