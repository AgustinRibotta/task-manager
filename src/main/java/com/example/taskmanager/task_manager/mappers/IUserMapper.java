package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { IRoleMapper.class, IProjectMapper.class })
public interface IUserMapper {

    @Mapping(source = "projectEntities", target = "projecDtos")
    @Mapping(source = "roleEntities", target = "roleDtos")
    UserDto userToUserDto(UserEntity user);

    @Mapping(source = "projecDtos", target = "projectEntities")
    @Mapping(source = "roleDtos", target = "roleEntities")
    UserEntity userDtoToUser(UserDto userDto);
}