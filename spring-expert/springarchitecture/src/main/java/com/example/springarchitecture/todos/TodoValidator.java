package com.example.springarchitecture.todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {

    // @Autowired -> field injection
    private TodoRepository repository;

    // Constructor injection, its better for modern spring,
    // immutable, testable and clear dependencies.
    public TodoValidator(TodoRepository repository) {
        this.repository = repository;
    }

    public void validate(TodoEntity todo) {
        if (existsDescTodo(todo.getDescription())) {
            throw new IllegalArgumentException("A todo with this description already exists.");
        }
    }

    private boolean existsDescTodo(String desc) {
        return repository.existsByDescription(desc);
    }
}
