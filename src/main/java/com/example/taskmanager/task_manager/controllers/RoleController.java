package com.example.taskmanager.task_manager.controllers;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.role.RoleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.task_manager.services.IRoleService;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@Tag(name = "Roles")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    final private IRoleService roleService;

     @Operation(summary = "Find all")
    @PreAuthorize("hasAuthority('roles:read:all')")
    @GetMapping()
    public ResponseEntity<List<RoleResponseDto>> getAll() {
        List<RoleResponseDto> response = this.roleService.getAll();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Find By id")
    @PreAuthorize("hasAuthority('roles:read')")
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getById (@PathVariable Long id) {
        RoleResponseDto response = this.roleService.getById(id);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Create New")
    @PostMapping()
    @PreAuthorize("hasAuthority('roles:create')")
    public ResponseEntity<RoleResponseDto> post(@RequestBody RoleRequestDto request) {

        RoleResponseDto response = this.roleService.post(request);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();

        return ResponseEntity.created(location).body(response) ;
    }
    
    @Operation(summary = "Update")
    @PreAuthorize("hasAuthority('roles:update')")
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDto> put(@PathVariable Long id, @RequestBody RoleRequestDto request) {
    
        RoleResponseDto response = this.roleService.put(id, request);
    
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete")
    @PreAuthorize("hasAuthority('roles:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PERMISSION
    @Operation(summary = "Assign Permission")
    @PreAuthorize("hasAuthority('roles:create')")
    @PostMapping("{roleId}/permission")
    public ResponseEntity<?> assignPermission (@PathVariable Long roleId, @RequestBody List<Long> request){
         this.roleService.assignPermissionToRole(roleId, request);
         return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove Permission")
    @PreAuthorize("hasAuthority('roles:create')")
    @DeleteMapping("{roleId}/permission/{permissionId}")
    public ResponseEntity<?> removePermission (@PathVariable Long roleId, @PathVariable Long permissionId ){
        this.roleService.removePermissionToRole(roleId, permissionId);
        return ResponseEntity.ok().build();
    }

}
