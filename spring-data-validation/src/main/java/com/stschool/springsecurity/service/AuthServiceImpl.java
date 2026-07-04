package com.stschool.springsecurity.service;

import com.stschool.springsecurity.dto.AuthResponseDto;
import com.stschool.springsecurity.dto.LoginRequestDto;
import com.stschool.springsecurity.dto.SignupRequestDto;
import com.stschool.springsecurity.dto.UserResponseDto;
import com.stschool.springsecurity.entity.User;
import com.stschool.springsecurity.exception.IllegalCredentialsException;
import com.stschool.springsecurity.exception.UserAlreadyExistException;
import com.stschool.springsecurity.exception.UserNotFoundException;
import com.stschool.springsecurity.security.JwtUtil;
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
    private final JwtUtil jwtUtil;

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
    public AuthResponseDto login(LoginRequestDto loginRequestDto)
            throws IllegalCredentialsException, UserNotFoundException {

        UserResponseDto userResponseDto =
                userService.findByEmail(loginRequestDto.getEmail());

        if (userResponseDto == null) {
            throw new IllegalCredentialsException(
                    "Invalid email or password");
        }

        if (!passwordEncoder.matches(
                loginRequestDto.getPassword(),
                userResponseDto.getPassword())) {

            throw new IllegalCredentialsException(
                    "Invalid email or password");
        }
        User user = modelMapper.map(userResponseDto, User.class);

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(userResponseDto)
                .build();

    }
}
