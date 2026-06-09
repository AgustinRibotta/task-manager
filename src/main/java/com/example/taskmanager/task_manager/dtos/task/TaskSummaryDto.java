package com.example.taskmanager.task_manager.dtos.task;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskSummaryDto {

    private long id;

    private String name;

}
