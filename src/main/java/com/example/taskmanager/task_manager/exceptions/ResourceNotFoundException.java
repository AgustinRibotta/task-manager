package com.example.taskmanager.task_manager.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found");
    }
}