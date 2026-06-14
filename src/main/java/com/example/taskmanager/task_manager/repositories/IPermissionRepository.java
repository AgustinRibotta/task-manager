package com.example.taskmanager.task_manager.repositories;

import com.example.taskmanager.task_manager.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
