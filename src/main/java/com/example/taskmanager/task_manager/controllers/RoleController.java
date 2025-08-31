package com.example.taskmanager.task_manager.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.task_manager.dtos.RoleDto;
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
    @GetMapping("")
    public ResponseEntity<List<RoleDto>> getAll() {

        List<RoleDto> roles = this.roleService.getAll(); 

        return ResponseEntity.ok().body(roles);
    }

    // GET - 200 OK - 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getById (@PathVariable Long id) {

        RoleDto roleDto = this.roleService.getById(id);

        return ResponseEntity.ok().body(roleDto);
    }

    // POST - 201 Created - 400 Bad Request - 409 Conflict
    @PostMapping("")
    public ResponseEntity<RoleDto> post(@RequestBody RoleDto roleDto) {

        RoleDto newRole = this.roleService.post(roleDto);  
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newRole.getId())
            .toUri();       

        return ResponseEntity.created(location).body(roleDto) ;
    }
    
    // PUT - 200 OK - 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> put(@PathVariable Long id, @RequestBody RoleDto roleDto) {
    
        RoleDto updaRole = this.roleService.put(id, roleDto); 
    
        return ResponseEntity.ok().body(updaRole);
    }

    // DELETE - 204 No Content - 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
