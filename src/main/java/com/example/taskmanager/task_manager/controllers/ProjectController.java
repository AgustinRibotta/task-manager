package com.example.taskmanager.task_manager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.task_manager.dtos.ProjecDto;
import com.example.taskmanager.task_manager.services.IProjectService;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final IProjectService projectService;

    // GET - 200 OK - [] - 401 Unauthorized
    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<ProjecDto>> getAll() {
        return ResponseEntity.ok(this.projectService.getAll());
    }

    // GET - 200 OK - [] - 401 Unauthorized
    @PreAuthorize("@securityConfigProject.isProject(#id) or hasRole('ADMIN')")
    @GetMapping("/project/{id}")
    public ResponseEntity<ProjecDto> getById(@PathVariable Long id) {
        ProjecDto projecDto = this.projectService.getById(id);

        return ResponseEntity.ok(projecDto);
    }
    
    @PostMapping("/project/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjecDto> post(@RequestBody  ProjecDto projecDto) {
        
        ProjecDto newProjecDto = this.projectService.post(projecDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newProjecDto.getId())
            .toUri();
        
        return ResponseEntity.created(location).body(newProjecDto);
    }
    
    @PutMapping("project/{id}")
    public ResponseEntity<ProjecDto> put(@PathVariable Long id, @RequestBody ProjecDto projecDto) {
        
        ProjecDto updateProjecDto = this.projectService.put(projecDto, id);

        return ResponseEntity.ok(updateProjecDto);
    }

    @DeleteMapping("project/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}