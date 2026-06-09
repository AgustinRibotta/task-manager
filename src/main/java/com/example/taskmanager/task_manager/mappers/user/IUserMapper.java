package com.example.taskmanager.task_manager.mappers.user;

import com.example.taskmanager.task_manager.dtos.user.UserRequestDto;
import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.mappers.role.IRoleMapper;
import com.example.taskmanager.task_manager.mappers.role.IRoleSummaryMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {IRoleSummaryMapper.class})
public interface IUserMapper {

    UserEntity toEntity (UserRequestDto dto);
    UserResponseDto toDto (UserEntity entity);

}