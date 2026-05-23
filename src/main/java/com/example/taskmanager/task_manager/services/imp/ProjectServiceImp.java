package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import com.example.taskmanager.task_manager.dtos.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.ProjectResponseDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceAlreadyExistsException;
import com.example.taskmanager.task_manager.exceptions.ResourceNotFoundException;
import com.example.taskmanager.task_manager.mappers.IProjectMapper;
import com.example.taskmanager.task_manager.repositories.IProjectRepository;
import com.example.taskmanager.task_manager.repositories.IUserRepository;
import com.example.taskmanager.task_manager.services.IProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements IProjectService {

    private final IProjectRepository projectRepository;
    private final IProjectMapper projectMapper;
    private final IUserRepository userRepository;

    @Override
    public List<ProjectResponseDto> getAll() {

        return this.projectRepository.findAll(Sort.by("id")).stream()
        .map(this.projectMapper::entityToResponse)
        .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDto getById(Long id) {

        ProjectEntity projectEntity = this.projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id)
            );

        return this.projectMapper.entityToResponse(projectEntity);
    }

    @Override
    public ProjectResponseDto post(ProjectRequestDto projectDto) {


        if (this.projectRepository.findByName(projectDto.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException(projectDto.getName());
        }

        ProjectEntity projectEntity = this.projectMapper.requestToEntity(projectDto);

        projectEntity = this.projectRepository.save(projectEntity);

        return this.projectMapper.entityToResponse(projectEntity);
    }

    @Override
    public ProjectResponseDto put(ProjectRequestDto projectDto, Long id) {

        this.projectRepository.findByName(projectDto.getName())
            .filter(existing -> existing.getId() != id)
            .ifPresent(existing -> {
                throw new ResourceAlreadyExistsException(projectDto.getName());
            });

        ProjectEntity projectEntity = this.projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));

        projectEntity.setName(projectDto.getName());
        projectEntity.setDescription(projectDto.getDescription());

        projectEntity = this.projectRepository.save(projectEntity);

        return this.projectMapper.entityToResponse(projectEntity);
    }

    @Override
    public void delete(Long id) {
        this.projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
            
        this.projectRepository.deleteById(id);
    }

	@Override
	public void deleteUserFromProjects(Long userId) {
        this.projectRepository.deleteUserFromProjects(userId);
    }

    @Override
    public List<ProjectResponseDto> findByUsersId(Long userId) {

        return this.projectRepository.findByUsers_Id(userId)
                .stream()
                .map(projectMapper::entityToResponse)
                .toList();
    }


}
