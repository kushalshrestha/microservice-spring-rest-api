package com.kushalshrestha.springboot.service.impl;

import com.kushalshrestha.springboot.dto.UserDto;
import com.kushalshrestha.springboot.entity.User;
import com.kushalshrestha.springboot.mapper.UserMapper;
import com.kushalshrestha.springboot.repository.UserRepository;
import com.kushalshrestha.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor  // will create constructor for UserRepository instance variable
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
//        convert userDto into User Jpa Entity
//        User user = UserMapper.mapToUser(userDto);

//        Using ModelMapper
        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
//        return UserMapper.mapToUserDto(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> usersList = userRepository.findAll();
//        return usersList.stream().map(UserMapper::mapToUserDto).toList();
        return usersList.stream().map((user) -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
