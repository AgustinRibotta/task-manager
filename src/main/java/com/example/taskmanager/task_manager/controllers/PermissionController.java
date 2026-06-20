package com.example.taskmanager.task_manager.controllers;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.task_manager.dtos.permission.PermissionDto;
import com.example.taskmanager.task_manager.services.IPermissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag( name = "Permission")
@RestController 
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {

    final private IPermissionService permissionService;

    @Operation( summary = "Find all")
    @PreAuthorize("hasAuthority('roles:read:all')")
    @GetMapping
    public ResponseEntity<List<PermissionDto>> getAll() {
        List<PermissionDto> response = this.permissionService.findAll();
        return ResponseEntity.ok().body(response);
    }
    
}
