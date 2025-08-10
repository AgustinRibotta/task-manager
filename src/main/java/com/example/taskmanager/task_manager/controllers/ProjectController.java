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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final IProjectService projectService;

    @GetMapping("/all")
    public ResponseEntity<List<ProjecDto>> getAll() {
        return ResponseEntity.ok(this.projectService.getAll());
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjecDto> getById(@PathVariable Long id) {
        ProjecDto projecDto = this.projectService.getById(id);

        return ResponseEntity.ok(projecDto);
    }
    
    @PostMapping("/project/new")
    public ResponseEntity<ProjecDto> post(@RequestBody  ProjecDto projecDto) {
        
        ProjecDto newProjecDto = this.projectService.post(projecDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newProjecDto.getId())
            .toUri();
        
        return ResponseEntity.created(location).body(newProjecDto);
    }
    

}