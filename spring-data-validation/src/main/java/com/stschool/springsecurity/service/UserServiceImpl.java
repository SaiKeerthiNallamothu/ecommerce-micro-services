package com.stschool.springsecurity.service;

import com.stschool.springsecurity.dto.UserResponseDto;
import com.stschool.springsecurity.entity.User;
import com.stschool.springsecurity.exception.UserAlreadyExistException;
import com.stschool.springsecurity.exception.UserNotFoundException;
import com.stschool.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    //  public UserServiceImpl(UserRepository userRepository) {
      //  this.userRepository = userRepository;
    //}


    @Override
    public UserResponseDto save(User user) throws UserAlreadyExistException {
        log.info("{} saving user {}",getClass().getSimpleName(),user);
        userRepository.findByEmail(user.getEmail()).ifPresent(user1->{
            throw new UserAlreadyExistException(
                    "User already exists with email: " + user1.getEmail());
        });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserResponseDto.class))
                .toList();
    }

    @Override

    public UserResponseDto findByEmail(String email) throws UserNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User with this email :"+email+"not found"));
        return modelMapper.map(user,UserResponseDto.class);
    }


    @Override
    public UserResponseDto update(String email,User user) throws UserNotFoundException {
       User updated = userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User with this email :"+email+"not found"));
       updated.setName(user.getName());
        User saved = userRepository.save(updated);
        return modelMapper.map(saved,UserResponseDto.class);
    }

    @Override
    public void deleteUser(String email) throws UserNotFoundException {
        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User with this email :"+email+"not found"));
        userRepository.delete(user);
    }

    @Override
    public boolean existsByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).isPresent();

    }
}
