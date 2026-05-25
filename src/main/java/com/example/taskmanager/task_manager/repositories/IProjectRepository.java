package com.example.taskmanager.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanager.task_manager.entities.ProjectEntity;

import java.util.List;
import java.util.Optional;


public interface IProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByName(String name);

    List<ProjectEntity> findByUsers_Id(Long userId);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM project_user WHERE user_id = :userId", nativeQuery = true)
    void deleteUserFromProjects(@Param("userId") Long user_id);
}
