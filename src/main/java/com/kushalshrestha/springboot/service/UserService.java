package com.kushalshrestha.springboot.service;

import com.kushalshrestha.springboot.dto.UserDto;
import com.kushalshrestha.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);

}
