package com.kushalshrestha.springboot.mapper;

import com.kushalshrestha.springboot.dto.UserDto;
import com.kushalshrestha.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    /*
    Inorder to use this mapping methods i.e mapToUserDto(), mapToUser(), we need to have implementation of this
    interface.
    An abstract provides implementation for this interface at compilation time.
    So, in order to get the implementation of this interface we can use Mappers Utility class.
     */
    AutoUserMapper MAPPER_INSTANCE = Mappers.getMapper(AutoUserMapper.class);
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
