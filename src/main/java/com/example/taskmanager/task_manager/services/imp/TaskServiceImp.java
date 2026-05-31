package com.example.taskmanager.task_manager.services.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;
import org.springframework.stereotype.Service;

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

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements ITaskService {

    private final ITaskRepository taskRepository;
    private final ITaskMapper taskMapper;
    private final IUserRepository userRepository;
    private final IProjectRepository projectRepository;

    @Override
    public List<TaskResponseDto> getAll() {

        return this.taskRepository.findAll().stream()
                .map(task -> {
                    TaskResponseDto dto = this.taskMapper.taskEntityTopTaskDto(task);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto getById(Long taskId) {

        TaskEntity taskEntity = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(taskId)
                );

        return this.taskMapper.taskEntityTopTaskDto(taskEntity);
    }

    @Override
    public TaskResponseDto post(TaskRequestDto request) {

        TaskEntity saved = createTask(request, null);

        return taskMapper.taskEntityTopTaskDto(saved);
    }

    @Override
    public TaskResponseDto postTaskProject(TaskRequestDto request, Long projectId) {

        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(projectId));

        TaskEntity saved = createTask(request, project);

        return taskMapper.taskEntityTopTaskDto(saved);
    }

    @Override
    public TaskResponseDto put(TaskRequestDto request, Long id) {

        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        if (request.getProjectId() != null) {

            boolean exists = taskRepository.existsByNameAndProject_Id(
                    request.getName(),
                    request.getProjectId()
            );

            if (exists && !task.getName().equals(request.getName())) {
                throw new ResourceAlreadyExistsException(
                        "Task with name already exists in this project"
                );
            }
        }

        task.setName(request.getName());
        task.setDescription(request.getDescription());

        Set<UserEntity> users = new HashSet<>(
                userRepository.findAllById(request.getUserId())
        );

        if (users.size() != request.getUserId().size()) {
            throw new ResourceNotFoundException(
                    "Some users not found"
            );
        }

        task.setUsers(users);

        if (request.getProjectId() != null) {

            ProjectEntity project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            request.getProjectId()
                    ));

            task.setProject(project);
        }

        TaskEntity saved = taskRepository.save(task);

        return taskMapper.taskEntityTopTaskDto(saved);
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
                .map(taskMapper::taskEntityTopTaskDto)
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

        TaskEntity task = taskMapper.tasktDtoToTaskEntity(request);

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
}
