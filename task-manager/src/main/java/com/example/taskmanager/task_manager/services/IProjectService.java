package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.ProjecDto;

public interface IProjectService {

    List<ProjecDto> getAll();
    ProjecDto getById (Long id);
    ProjecDto post (ProjecDto projecDto);
    ProjecDto put (ProjecDto projecDto, Long id);
    void delete (Long id);
    
}
