package com.stschool.springsecurity.service;

import com.stschool.springsecurity.dto.LoginRequestDto;
import com.stschool.springsecurity.dto.SignupRequestDto;
import com.stschool.springsecurity.dto.UserResponseDto;
import com.stschool.springsecurity.entity.User;
import com.stschool.springsecurity.exception.IllegalCredentialsException;
import com.stschool.springsecurity.exception.UserAlreadyExistException;
import com.stschool.springsecurity.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService{
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponseDto signup(SignupRequestDto signupRequestDto) throws UserAlreadyExistException {
        if(userService.existsByEmail(signupRequestDto.getEmail())){
            throw new UserAlreadyExistException(
                    "User already exists with email : "
                            + signupRequestDto.getEmail()
            );
        }
        User user = modelMapper.map(signupRequestDto, User.class);
        return userService.save(user);
    }

    @Override
    public LoginRequestDto login(LoginRequestDto loginRequestDto) throws IllegalCredentialsException, UserNotFoundException {
        UserResponseDto userResponseDto = userService.findByEmail(loginRequestDto.getEmail());
        if(userResponseDto == null){
            throw new IllegalCredentialsException("Invalid email or password");
        }
        if(!passwordEncoder.matches(loginRequestDto.getPassword(),userResponseDto.getEmail())){
            throw new IllegalCredentialsException("Invalid email or password");
        }
        return null;
    }
}
