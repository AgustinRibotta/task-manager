package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.dtos.UserSummaryDto;
import com.example.taskmanager.task_manager.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface IUserForProjectMapper {

    UserSummaryDto userEntityToUserDtoForProjectDto(UserEntity userEntity);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "projectEntities", ignore = true)
    @Mapping(target = "roleEntities", ignore = true)
    @Mapping(target = "taskEntities", ignore = true)
    UserEntity userDtoForProjectDtoToUserEntity(UserSummaryDto dto);
}