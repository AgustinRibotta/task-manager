package com.example.taskmanager.task_manager.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(hidden = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDto {

    private Long id;
    
    @NotBlank(message = "Username is required")
    private String username;

}
