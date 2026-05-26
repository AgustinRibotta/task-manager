package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-26T21:15:50+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class IRoleMapperImpl implements IRoleMapper {

    @Override
    public RoleDto roleEntityToRoleDto(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( entity.getId() );
        roleDto.setName( entity.getName() );

        return roleDto;
    }

    @Override
    public RoleEntity roleDtoToRoleEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( dto.getId() );
        roleEntity.setName( dto.getName() );

        return roleEntity;
    }
}
