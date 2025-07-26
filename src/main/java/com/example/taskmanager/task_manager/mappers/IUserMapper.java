package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { IRoleMapper.class })
public interface IUserMapper {

    UserDto userToUserDto(UserEntity user);

    UserEntity userDtoToUser(UserDto userDto);
}