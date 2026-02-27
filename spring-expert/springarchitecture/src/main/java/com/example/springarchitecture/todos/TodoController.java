package com.example.springarchitecture.todos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/todos")
public class TodoController {

    /*
     * Controller Layer
     * Public API/HTTP entry point (routes -> methods).
     * Validates input, converts DTOs <-> domain objects.
     * Delegates business logic to the service layer.
     * Translates exceptions to HTTP responses/status codes.
     */

    private TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public TodoEntity save(@RequestBody TodoEntity todo) {
        try {
            return this.service.save(todo);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void attStatus(
            @PathVariable Integer id, @RequestBody TodoEntity todo) {
        todo.setId(id);
        this.service.attStatus(todo);
    }

    @GetMapping("/{id}")
    public TodoEntity getTodo(@PathVariable Integer id) {
        return service.getTodoById(id);
    }
}
