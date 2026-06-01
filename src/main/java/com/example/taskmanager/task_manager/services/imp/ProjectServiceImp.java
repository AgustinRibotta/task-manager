package com.example.taskmanager.task_manager.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.user.AssignUsersRequest;
import com.example.taskmanager.task_manager.entities.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceAlreadyExistsException;
import com.example.taskmanager.task_manager.exceptions.ResourceNotFoundException;
import com.example.taskmanager.task_manager.mappers.project.IProjectMapper;
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
    public List<ProjectResponseDto> findAll() {

        return this.projectRepository.findAll(Sort.by("id")).stream()
        .map(this.projectMapper::entityToResponse)
        .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDto findById(Long id) {
        ProjectEntity projectEntity = this.projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id)
            );
        return this.projectMapper.entityToResponse(projectEntity);
    }

    @Override
    public ProjectResponseDto create(ProjectRequestDto request) {
        if (this.projectRepository.findByName(request.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException(request.getName());
        }
        ProjectEntity projectEntity = this.projectMapper.requestToEntity(request);

        UserEntity userEntity = this.userRepository.findById(request.getOwner())
                .orElseThrow(() -> new ResourceNotFoundException(request.getOwner()));

        projectEntity.setOwner(userEntity);
        projectEntity = this.projectRepository.save(projectEntity);
        return this.projectMapper.entityToResponse(projectEntity);
    }

    @Override
    public ProjectResponseDto update(ProjectRequestDto request, Long id) {
        this.projectRepository.findByName(request.getName())
            .filter(existing -> existing.getId() != id)
            .ifPresent(existing -> {
                throw new ResourceAlreadyExistsException(request.getName());
            });

        ProjectEntity projectEntity = this.projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));

        ProjectEntity finalProjectEntity = projectEntity;
        UserEntity userEntity = this.userRepository.findById(projectEntity.getOwner().getId())
                .orElseThrow(() -> new ResourceNotFoundException(finalProjectEntity.getOwner().getId()));

        projectEntity.setOwner(userEntity);
        projectEntity.setName(request.getName());
        projectEntity.setDescription(request.getDescription());
        projectEntity = this.projectRepository.save(projectEntity);

        return this.projectMapper.entityToResponse(projectEntity);
    }

    @Override
    public void changeOwner(Long projectId, Long ownerId) {
        ProjectEntity projectEntity = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(projectId));
        UserEntity userEntity = this.userRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(ownerId));

        projectEntity.setOwner(userEntity);
        this.projectRepository.save(projectEntity);
    }

    @Override
    public void assignUsersToProject(Long projectId, AssignUsersRequest request) {
        ProjectEntity project = this.projectRepository.findById(projectId)
                .orElseThrow();

        List<UserEntity> users = userRepository.findAllById(request.getUserIds());

        project.getUsers().addAll(users);
        projectRepository.save(project);
    }

    @Override
    public void removeUserFromAllProject(Long projectId, Long userId) {

        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(projectId));
        project.getUsers().removeIf(u -> u.getId().equals(userId));

        projectRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        this.projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
            
        this.projectRepository.deleteById(id);
    }

	@Override
	public void removeUserFromAllProject(Long userId) {
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
