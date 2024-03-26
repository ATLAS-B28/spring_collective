package com.example.todo.service.implementation;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.repository.TodoRepository;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        Todo todo = TodoMapper.mapToTodo(todoDto);
        Todo saveTodo = todoRepository.save(todo);
        return TodoMapper.mapToTodoDto(saveTodo);
    }

    @Override
    public TodoDto getTodo(Long id) {

        Todo getTodo = todoRepository
                         .findById(id)
                         .orElseThrow(
                                 () -> new RuntimeException("Todo not found")
                         );

        return TodoMapper.mapToTodoDto(getTodo);
    }

    @Override
    public List<TodoDto> getAllTodo() {

        List<Todo> getAllTodo = todoRepository.findAll();
        return getAllTodo.stream().map(TodoMapper::mapToTodoDto).toList();
    }

    @Override
    public String deleteTodo(Long id) {

        Todo getTodo = todoRepository
                         .findById(id)
                         .orElseThrow(
                                 () -> new RuntimeException("Todo not found")
                         );
        todoRepository.deleteById(id);

        return "Todo deleted successfully";
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {

        Todo todo = todoRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Todo not found")
                );
        todo.setId(id);
        todo.setTitle(todoDto.title());
        todo.setDescription(todoDto.description());
        todo.setCompleted(todoDto.completed());
        Todo savedTodo = todoRepository.save(todo);

        return TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo", "id", id));

        todo.setCompleted(Boolean.TRUE);

        Todo updatedTodo = todoRepository.save(todo);

        return TodoMapper.mapToTodoDto(updatedTodo);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo", "id", id));

        todo.setCompleted(Boolean.FALSE);

        Todo updatedTodo = todoRepository.save(todo);

        return TodoMapper.mapToTodoDto(updatedTodo);
    }
}
