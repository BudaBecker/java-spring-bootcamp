package com.example.springarchitecture.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    /*
     * The Service layer
     * Holds business logic and transaction boundaries.
     * Orchestrates calls to repositories, validators, mappers, etc.
     * Shields controllers (or other entry points) from persistence details.
     * Keeps domain rules in one place, easier testing and reuse.
     */

    private TodoRepository repository;
    private TodoValidator validator;
    private MailSender mailSender;

    public TodoService(TodoRepository repository, TodoValidator validator, MailSender mailSender) {
        this.repository = repository;
        this.validator = validator;
        this.mailSender = mailSender;
    }

    public TodoEntity save(TodoEntity todoEntity) {
        validator.validate(todoEntity);
        return repository.save(todoEntity);
    }

    public void attStatus(TodoEntity todo) {
        repository.save(todo);
        String status = todo.getConcluded() == Boolean.TRUE ? "done" : "not done";
        mailSender.send("Todo "
                + todo.getDescription()
                + "is "
                + status);
    }

    public TodoEntity getTodoById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
