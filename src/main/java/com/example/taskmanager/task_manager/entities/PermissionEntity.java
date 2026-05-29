package com.example.taskmanager.task_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table( name = "permission" )
public class PermissionEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column( unique = true )
    private String  name;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles;

}
