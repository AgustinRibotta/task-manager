package com.example.taskmanager.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskmanager.task_manager.entities.ProjectEntity;
import java.util.Optional;


public interface IProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByName(String name);
}
