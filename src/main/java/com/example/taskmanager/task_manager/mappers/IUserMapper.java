package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserDto userToUserDto(UserEntity user);

    UserEntity userDtoToUser(UserDto userDto);
}
