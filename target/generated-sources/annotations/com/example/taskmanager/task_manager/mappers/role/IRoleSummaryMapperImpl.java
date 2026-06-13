package com.example.taskmanager.task_manager.mappers.role;

import com.example.taskmanager.task_manager.dtos.role.RoleSummaryDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-13T22:40:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Arch Linux)"
)
@Component
public class IRoleSummaryMapperImpl implements IRoleSummaryMapper {

    @Override
    public RoleSummaryDto toDto(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleSummaryDto roleSummaryDto = new RoleSummaryDto();

        roleSummaryDto.setId( entity.getId() );
        roleSummaryDto.setName( entity.getName() );

        return roleSummaryDto;
    }
}
