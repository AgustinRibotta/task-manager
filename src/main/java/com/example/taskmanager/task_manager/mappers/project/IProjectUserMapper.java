package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectUserDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.mappers.IRoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IRoleMapper.class})
public interface IProjectUserMapper {

    @Mapping(source = "roles", target = "roles")
    ProjectUserDto toProjectTaskDto(UserEntity entity);
}
