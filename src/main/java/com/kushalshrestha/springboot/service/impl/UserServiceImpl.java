package com.kushalshrestha.springboot.service.impl;

import com.kushalshrestha.springboot.dto.UserDto;
import com.kushalshrestha.springboot.entity.User;
import com.kushalshrestha.springboot.exception.EmailAlreadyExistsException;
import com.kushalshrestha.springboot.exception.ResourceNotFoundException;
import com.kushalshrestha.springboot.mapper.AutoUserMapper;
import com.kushalshrestha.springboot.mapper.UserMapper;
import com.kushalshrestha.springboot.repository.UserRepository;
import com.kushalshrestha.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor  // will create constructor for UserRepository instance variable
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists for User");
        }
        /* convert userDto into User Jpa Entity */
//        User user = UserMapper.mapToUser(userDto);

//        /* Using ModelMapper */
//        User user = modelMapper.map(userDto, User.class);

        User user = AutoUserMapper.MAPPER_INSTANCE.mapToUser(userDto);

        User savedUser = userRepository.save(user);
        /* Using model mapper */
//        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        UserDto savedUserDto = AutoUserMapper.MAPPER_INSTANCE.mapToUserDto(savedUser);
        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//        return UserMapper.mapToUserDto(user);
//        return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER_INSTANCE.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> usersList = userRepository.findAll();
//        return usersList.stream().map(UserMapper::mapToUserDto).toList();
//        return usersList.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return usersList.stream().map((user) -> AutoUserMapper.MAPPER_INSTANCE.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER_INSTANCE.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.deleteById(userId);
    }
}
