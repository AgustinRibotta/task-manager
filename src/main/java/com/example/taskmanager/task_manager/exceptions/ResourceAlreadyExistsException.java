package com.example.taskmanager.task_manager.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {


    public ResourceAlreadyExistsException(String resourceName) {
        super("Resource name '" + resourceName + "' is already taken");
    }

    public ResourceAlreadyExistsException(Long id) {
        super("Resource with ID '" + id + "' already exists");
    }
}