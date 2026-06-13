package com.example.taskmanager.task_manager.mappers.user;

import com.example.taskmanager.task_manager.dtos.user.UserSummaryDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface IUserSummaryMapper {
    UserSummaryDto toDto (UserEntity entity);
}
