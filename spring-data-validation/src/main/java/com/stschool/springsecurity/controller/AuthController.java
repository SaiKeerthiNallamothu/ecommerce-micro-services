package com.stschool.springsecurity.controller;


import com.stschool.springsecurity.dto.AuthResponseDto;
import com.stschool.springsecurity.dto.LoginRequestDto;
import com.stschool.springsecurity.dto.SignupRequestDto;
import com.stschool.springsecurity.dto.UserResponseDto;
import com.stschool.springsecurity.exception.IllegalCredentialsException;
import com.stschool.springsecurity.exception.UserAlreadyExistException;
import com.stschool.springsecurity.exception.UserNotFoundException;
import com.stschool.springsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) throws UserAlreadyExistException{
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signupRequestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto)throws IllegalCredentialsException, UserNotFoundException {
        return ResponseEntity.ok(authService.login(loginRequestDto));

    }

}
