package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.project.PermissionDto;
import com.example.taskmanager.task_manager.entities.PermissionEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-31T12:22:59+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IPermissionDtoImpl implements IPermissionDto {

    @Override
    public PermissionDto toPermissionDto(PermissionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId( entity.getId() );
        permissionDto.setName( entity.getName() );

        return permissionDto;
    }

    @Override
    public PermissionEntity toPermissionEntity(PermissionDto dto) {
        if ( dto == null ) {
            return null;
        }

        PermissionEntity permissionEntity = new PermissionEntity();

        if ( dto.getId() != null ) {
            permissionEntity.setId( dto.getId() );
        }
        permissionEntity.setName( dto.getName() );

        return permissionEntity;
    }
}
