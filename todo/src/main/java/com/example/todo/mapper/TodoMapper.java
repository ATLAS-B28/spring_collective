package com.example.todo.mapper;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;

public class TodoMapper {

    public static Todo mapToTodo(TodoDto todoDto) {
        return new Todo(
            todoDto.id(),
            todoDto.title(),
            todoDto.description(),
            todoDto.completed()
        );
    }

    public static TodoDto mapToTodoDto(Todo todo) {
        return new TodoDto(
          todo.getId(),
          todo.getTitle(),
          todo.getDescription(),
          todo.isCompleted()
        );
    }
}
