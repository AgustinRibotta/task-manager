package com.example.taskmanager.task_manager.services.imp;

import com.example.taskmanager.task_manager.dtos.permission.PermissionDto;
import com.example.taskmanager.task_manager.mappers.IPermissionDto;
import com.example.taskmanager.task_manager.repositories.IPermissionRepository;
import com.example.taskmanager.task_manager.services.IPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements IPermissionService {

    private final  IPermissionRepository permissionRepository;
    private final IPermissionDto permissionDto;

    @Override
    public List<PermissionDto> findAll() {

        return this.permissionRepository.findAll(Sort.by("id")).stream()
                .map(this.permissionDto::toPermissionDto)
                .collect(Collectors.toList());
    }
}
