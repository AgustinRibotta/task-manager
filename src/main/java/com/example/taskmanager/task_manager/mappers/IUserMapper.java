package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {IRoleMapper.class})
public interface IUserMapper {

    @Mapping(source = "projectEntities", target = "projectResponseDto")
    @Mapping(source = "roleEntities", target = "roleDto")
    @Mapping(source = "taskEntities", target = "taskDto")
    UserDto userToUserDto(UserEntity user);

    @Mapping(source = "projectResponseDto", target = "projectEntities")
    @Mapping(source = "roleDto", target = "roleEntities")
    @Mapping(source = "taskDto", target = "taskEntities")
    UserEntity userDtoToUser(UserDto userDto);
}