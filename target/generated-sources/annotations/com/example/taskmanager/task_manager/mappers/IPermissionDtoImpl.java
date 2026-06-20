package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.permission.PermissionDto;
import com.example.taskmanager.task_manager.entities.PermissionEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-20T14:23:06+0200",
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

        permissionEntity.setId( dto.getId() );
        permissionEntity.setName( dto.getName() );

        return permissionEntity;
    }
}
