package com.example.taskmanager.task_manager.repositories;

import com.opencsv.bean.CsvToBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanager.task_manager.entities.TaskEntity;

import java.util.List;
import java.util.Optional;


public interface ITaskRepository extends JpaRepository <TaskEntity, Long> {

    boolean existsByNameAndProject_Id(String name, Long projectId);

    List<TaskEntity> findByUsers_Id (Long id);

    @Modifying
    @Transactional
    @Query( value = "DELETE FROM task_user WHERE user_id = :userId", nativeQuery = true)
    void deleteUserFromTask(@Param("userId") Long user_id);


}
