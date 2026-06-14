package com.example.taskmanager.task_manager.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Schema(hidden = true)
@Entity
@Getter
@Setter
@Table( name = "permission" )
public class PermissionEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( unique = true )
    private String  name;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles;

}
