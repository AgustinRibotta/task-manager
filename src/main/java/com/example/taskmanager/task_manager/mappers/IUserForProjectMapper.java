package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;

import com.example.taskmanager.task_manager.dtos.UserSummaryDto;
import com.example.taskmanager.task_manager.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface IUserForProjectMapper {
    UserSummaryDto userEntityToUserDtoForProjectDto(UserEntity userEntity);
    UserEntity userDtoForProjectDtoToUserEntity(UserSummaryDto dto);
}