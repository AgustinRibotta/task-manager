package com.example.taskmanager.task_manager.openApi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Manager")
                        .version("1.0")
                        .description("""
                        | Endpoint                 | Method | Roles / Access                                     | Description          |
                        | ------------------------ | ------ | -------------------------------------------------- | -------------------- |
                        | `/auth/users`            | GET    | ADMIN                                              | Get all users        |
                        | `/auth/users/{id}`       | GET    | ADMIN or the user itself (`isUser(#id)`)           | Get user by ID       |
                        | `/auth/users/{id}`       | PUT    | ADMIN                                              | Update user by ID    |
                        | `/auth/users/{id}`       | DELETE | ADMIN                                              | Delete user by ID    |
                        | `/roles`                 | GET    | ADMIN                                              | Get all roles        |
                        | `/roles/{id}`            | GET    | ADMIN                                              | Get role by ID       |
                        | `/roles`                 | POST   | ADMIN                                              | Create new role      |
                        | `/roles/{id}`            | PUT    | ADMIN                                              | Update role          |
                        | `/roles/{id}`            | DELETE | ADMIN                                              | Delete role          |
                        | `/projects/all`          | GET    | ADMIN                                              | Get all projects     |
                        | `/projects/project/{id}` | GET    | ADMIN or associated users (`isProject(#id)`)       | Get project by ID    |
                        | `/projects/project/new`  | POST   | ADMIN                                              | Create new project   |
                        | `/projects/project/{id}` | PUT    | ADMIN, MANAGER                                     | Update project by ID |
                        | `/projects/project/{id}` | DELETE | ADMIN                                              | Delete project by ID |
                        | `/tasks/all`             | GET    | ADMIN                                              | Get all tasks        |
                        | `/tasks/task/{id}`       | GET    | ADMIN, MANAGER or associated users (`isTask(#id)`) | Get task by ID       |
                        | `/tasks/task/new`        | POST   | ADMIN, MANAGER                                     | Create new task      |
                        | `/tasks/task/{id}`       | PUT    | ADMIN, MANAGER or associated users (`isTask(#id)`) | Update task by ID    |
                        | `/tasks/task/{id}`       | DELETE | ADMIN, MANAGER                                     | Delete task by ID    |
                        """))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                );
    }
}