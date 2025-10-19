package com.example.taskmanager.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.taskmanager.task_manager.entities.UserEntity;

import java.util.Optional;


public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT DISTINCT u FROM UserEntity u JOIN FETCH u.projectEntities WHERE u.username = :username")
    Optional<UserEntity> findByUsernameWithProjects(@Param("username") String username);

    @Query("SELECT u FROM UserEntity u JOIN FETCH u.taskEntities WHERE u.username = :username")
    Optional<UserEntity> findByUsernameWithTask(@Param("username") String username);

}