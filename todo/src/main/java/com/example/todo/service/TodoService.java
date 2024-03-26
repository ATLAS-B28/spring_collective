package com.example.todo.service;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodo();

    String deleteTodo(Long id);

    TodoDto updateTodo(Long id,TodoDto todoDto);

    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);
}
