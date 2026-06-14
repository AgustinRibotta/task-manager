package com.example.taskmanager.task_manager.mappers.role;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.role.RoleResponseDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-14T15:20:17+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IRoleMapperImpl implements IRoleMapper {

    @Override
    public RoleEntity toEntity(RoleRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( dto.getId() );
        roleEntity.setName( dto.getName() );

        return roleEntity;
    }

    @Override
    public RoleResponseDto toDto(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleResponseDto roleResponseDto = new RoleResponseDto();

        roleResponseDto.setId( entity.getId() );
        roleResponseDto.setName( entity.getName() );

        return roleResponseDto;
    }
}
