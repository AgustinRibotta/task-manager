package com.example.taskmanager.task_manager.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName) {
        super("Resource '" + resourceName + "' not found");
    }

    public ResourceNotFoundException(Long id) {
        super("Resource with ID '" + id + "' not found");
    }
}
