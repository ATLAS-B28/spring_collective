package com.example.todo.controller;

import com.example.todo.dto.TodoDto;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todos/")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("create-todo")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.addTodo(todoDto), HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TodoDto>> getAllTodo() {
        return new ResponseEntity<>(todoService.getAllTodo(), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.deleteTodo(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.updateTodo(id, todoDto), HttpStatus.OK);
    }

    @PatchMapping("/update-status/{id}")
    public ResponseEntity<TodoDto> updateTodoStatus(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.completeTodo(id), HttpStatus.OK);
    }

    @PatchMapping("/update-status-incomplete/{id}")
    public ResponseEntity<TodoDto> updateTodoStatusInComplete(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.inCompleteTodo(id), HttpStatus.OK);
    }
}
