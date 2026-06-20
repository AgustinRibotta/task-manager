package com.example.taskmanager.task_manager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanager.task_manager.entities.RoleEntity;


public interface IRoleRepository extends JpaRepository<RoleEntity, Long>{
    Optional<RoleEntity> findByName(String name);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_roles WHERE role_id = :roleId", nativeQuery = true)
    void deleteRoleUsers(@Param("roleId") Long id);
}
