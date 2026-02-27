package com.spring.demo.todos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService service;

    public TodoController(TodoService todoService) {
        this.service = todoService;
    }

    @PostMapping("/salvar")
    public TodoEntity salvar(@RequestBody TodoEntity todo) {
        return service.salvarTodo(todo);
    }

    @PutMapping("att/{id}")
    public TodoEntity atualizarStatus(@PathVariable("id") Integer id, @RequestBody TodoEntity todo) {
        todo.setId(id);
        return service.attTodo(todo);
    }

    @GetMapping("{id}")
    public TodoEntity buscar(@PathVariable("id") Integer id) {
        return service.buscarPorId(id);
    }

}
