package com.spring.demo.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    // Criando camada de repository em service
    private TodoRepository repository;

    public TodoService(TodoRepository todoRepository) {
        this.repository = todoRepository;
    }

    public TodoEntity salvarTodo(TodoEntity novoTodo) {
        return repository.save(novoTodo);
    }

    public TodoEntity buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

}
