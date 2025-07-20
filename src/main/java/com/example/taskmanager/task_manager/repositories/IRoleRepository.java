package com.example.taskmanager.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskmanager.task_manager.entities.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long>{
}
