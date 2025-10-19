package com.example.taskmanager.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskmanager.task_manager.entities.TaskEntity;
import java.util.Optional;


public interface ITaskRepository extends JpaRepository <TaskEntity, Long> {

    boolean existsByNameAndProjectEntity_Id(String taskName, Long projectId);
    Optional<TaskEntity> findByName(String name);
}
