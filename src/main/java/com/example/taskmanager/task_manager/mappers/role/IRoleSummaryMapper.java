package com.example.taskmanager.task_manager.mappers.role;

import com.example.taskmanager.task_manager.dtos.role.RoleSummaryDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleSummaryMapper {
    RoleSummaryDto toDto (RoleEntity entity);
}
