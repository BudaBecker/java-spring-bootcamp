package com.spring.demo.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    // Criando camada de repository em service
    private TodoRepository repository;
    private TodoValidator validator;
    private MailSender mailSender;

    public TodoService(TodoRepository todoRepository, TodoValidator validator, MailSender mailSender) {
        this.repository = todoRepository;
        this.validator = validator;
        this.mailSender = mailSender;
    }

    public TodoEntity salvarTodo(TodoEntity novoTodo) {
        validator.validar(novoTodo);
        return repository.save(novoTodo);
    }

    public TodoEntity attTodo(TodoEntity novoTodo) {
        mailSender.enviar("Todo: " + novoTodo);
        return repository.save(novoTodo);
    }

    public TodoEntity buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

}
