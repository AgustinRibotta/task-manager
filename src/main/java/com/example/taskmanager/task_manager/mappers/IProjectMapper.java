package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;

import com.example.taskmanager.task_manager.dtos.ProjecDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;

@Mapper(componentModel = "spring", uses = { IRoleMapper.class, IUserMapper.class, ITaskMapper.class })
public interface IProjectMapper {

    ProjecDto projectEntityToProjecDto (ProjectEntity projectEntity);

    ProjectEntity projectDtoToProjectEntity (ProjecDto projecDto);
}
