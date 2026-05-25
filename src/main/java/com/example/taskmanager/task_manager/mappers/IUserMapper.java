package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {IRoleMapper.class})
public interface IUserMapper {

    @Mapping(source = "projects", target = "projects")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "tasks", target = "tasks")
    UserDto userToUserDto(UserEntity user);

    @Mapping(source = "projects", target = "projects")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "tasks", target = "tasks")
    UserEntity userDtoToUser(UserDto userDto);
}