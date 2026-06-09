package com.example.taskmanager.task_manager.services.imp;

import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;
import com.example.taskmanager.task_manager.dtos.task.TaskUpdateRequest;
import com.example.taskmanager.task_manager.dtos.user.UsersAssignRequestDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;
import com.example.taskmanager.task_manager.entities.TaskEntity;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.exceptions.ResourceAlreadyExistsException;
import com.example.taskmanager.task_manager.exceptions.ResourceNotFoundException;
import com.example.taskmanager.task_manager.mappers.task.ITaskMapper;
import com.example.taskmanager.task_manager.repositories.IProjectRepository;
import com.example.taskmanager.task_manager.repositories.ITaskRepository;
import com.example.taskmanager.task_manager.repositories.IUserRepository;
import com.example.taskmanager.task_manager.services.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements ITaskService {

    private final ITaskRepository taskRepository;
    private final ITaskMapper taskMapper;
    private final IUserRepository userRepository;
    private final IProjectRepository projectRepository;

    @Override
    public List<TaskResponseDto> findAll() {

        return this.taskRepository.findAll().stream()
                .map(task -> {
                    TaskResponseDto dto = this.taskMapper.toDto(task);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto findById(Long taskId) {

        TaskEntity taskEntity = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(taskId)
                );

        return this.taskMapper.toDto(taskEntity);
    }

    @Override
    public TaskResponseDto create(TaskRequestDto request) {

        TaskEntity saved = createTask(request, null);

        return taskMapper.toDto(saved);
    }

    @Override
    public TaskResponseDto postTaskProject(TaskRequestDto request, Long projectId) {

        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(projectId));

        TaskEntity saved = createTask(request, project);
        return taskMapper.toDto(saved);
    }

    @Override
    public TaskResponseDto update(TaskUpdateRequest request, Long id) {

        TaskEntity entity = this.taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        this.taskRepository.save(entity);
        return this.taskMapper.toDto(entity);
    }

    @Override
    public void delete(Long taskId) {

        TaskEntity taskEntity = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(taskId)
                );

        this.taskRepository.delete(taskEntity);
    }

    @Override
    public void deleteUserFromTask(Long userId) {

        this.taskRepository.deleteUserFromTask(userId);
    }

    @Override
    public List<TaskResponseDto> findByUsersId(Long id) {
        return this.taskRepository.findByUsers_Id(id)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    private TaskEntity createTask(TaskRequestDto request, ProjectEntity project) {
        if (project != null) {
            boolean exists = taskRepository.existsByNameAndProject_Id(
                    request.getName(),
                    project.getId()
            );

            if (exists) {
                throw new ResourceAlreadyExistsException("Task already exists in project");
            }
        }

        TaskEntity task = taskMapper.toEntity(request);

        Set<UserEntity> users = new HashSet<>(
                userRepository.findAllById(request.getUserId())
        );

        if (users.size() != request.getUserId().size()) {
            throw new ResourceNotFoundException("Some users not found");
        }

        task.setUsers(users);
        task.setProject(project);

        return taskRepository.save(task);
    }

    @Override
    public void assignUsersToProject(Long taskId, UsersAssignRequestDto request) {
        TaskEntity task = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(taskId));

        List<UserEntity> users = this.userRepository.findAllById(request.getUserIds());

        task.getUsers().addAll(users);
        this.taskRepository.save(task);
    }

    @Override
    public void removeUsersFromProject(Long taskId, Long userId) {
        TaskEntity task = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(taskId));
        this.userRepository.existsById(userId);

        task.getUsers().removeIf(user -> user.getId().equals(userId));

        this.taskRepository.save(task);
    }
}
