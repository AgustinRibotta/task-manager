package com.example.taskmanager.task_manager.controllers;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
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



@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    final private IRoleService roleService;

     // GET - 200 OK - []
    @PreAuthorize("hasAuthority('roles:read:all')")
    @GetMapping()
    public ResponseEntity<List<RoleRequestDto>> getAll() {
        List<RoleRequestDto> response = this.roleService.getAll();
        return ResponseEntity.ok().body(response);
    }

    // GET - 200 OK - 404 Not Found
    @PreAuthorize("hasAuthority('roles:read')")
    @GetMapping("/{id}")
    public ResponseEntity<RoleRequestDto> getById (@PathVariable Long id) {
        RoleRequestDto response = this.roleService.getById(id);
        return ResponseEntity.ok().body(response);
    }

    // POST - 201 Created - 400 Bad Request - 409 Conflict
    @PostMapping()
    @PreAuthorize("hasAuthority('roles:create')")
    public ResponseEntity<RoleRequestDto> post(@RequestBody RoleRequestDto request) {

        RoleRequestDto response = this.roleService.post(request);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();

        return ResponseEntity.created(location).body(response) ;
    }
    
    // PUT - 200 OK - 404 Not Found
    @PreAuthorize("hasAuthority('roles:update')")
    @PutMapping("/{id}")
    public ResponseEntity<RoleRequestDto> put(@PathVariable Long id, @RequestBody RoleRequestDto rquest) {
    
        RoleRequestDto response = this.roleService.put(id, rquest);
    
        return ResponseEntity.ok().body(response);
    }

    // DELETE - 204 No Content - 404 Not Found
    @PreAuthorize("hasAuthority('roles:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
