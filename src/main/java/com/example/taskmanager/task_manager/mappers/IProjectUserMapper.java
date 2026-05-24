package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectUserDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IRoleMapper.class})
public interface IProjectUserMapper {

    @Mapping(source = "roleEntities", target = "roleDto")
    ProjectUserDto toProjectTaskDto(UserEntity entity);
}
