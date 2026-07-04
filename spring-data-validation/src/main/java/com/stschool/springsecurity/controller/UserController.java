package com.stschool.springsecurity.controller;

import com.stschool.springsecurity.dto.UserResponseDto;
import com.stschool.springsecurity.entity.User;
import com.stschool.springsecurity.exception.UserAlreadyExistException;
import com.stschool.springsecurity.exception.UserNotFoundException;
import com.stschool.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {
    public final UserService userService;
    @PostMapping("/save")
    public ResponseEntity<UserResponseDto> save(@RequestBody User user)throws  UserAlreadyExistException{
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getByEmail(@PathVariable String email) throws UserNotFoundException {

        return ResponseEntity.ok(userService.findByEmail(email));
    }
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }
    @PutMapping("/update")
    public  ResponseEntity<UserResponseDto> updateUser(@PathVariable String email,@RequestBody User user
    ) throws UserNotFoundException{
        return ResponseEntity.ok(userService.update(email,user));

    }
    @DeleteMapping("/delete")
    public ResponseEntity<?>deleteUser(@PathVariable String email) throws UserNotFoundException {
        userService.deleteUser(email);
        return ResponseEntity.ok("User deleted successfully");
    }

}
