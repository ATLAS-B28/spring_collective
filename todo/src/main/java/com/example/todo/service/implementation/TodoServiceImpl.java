package com.example.todo.service.implementation;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.repository.TodoRepository;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        Todo todo = modelMapper.map(todoDto, Todo.class);//TodoMapper.mapToTodo(todoDto);
        Todo saveTodo = todoRepository.save(todo);
        return modelMapper.map(saveTodo, TodoDto.class);//TodoMapper.mapToTodoDto(saveTodo);
    }

    @Override
    public TodoDto getTodo(Long id) {

        Todo getTodo = todoRepository
                         .findById(id)
                         .orElseThrow(
                                 () -> new RuntimeException("Todo not found")
                         );

        return modelMapper.map(getTodo, TodoDto.class);//TodoMapper.mapToTodoDto(getTodo);
    }

    @Override
    public List<TodoDto> getAllTodo() {

        List<Todo> getAllTodo = todoRepository.findAll();
        return getAllTodo
                .stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .toList();
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

        return modelMapper.map(savedTodo, TodoDto.class);//TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo", "id", id));

        todo.setCompleted(Boolean.TRUE);

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);//TodoMapper.mapToTodoDto(updatedTodo);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo", "id", id));

        todo.setCompleted(Boolean.FALSE);

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);//TodoMapper.mapToTodoDto(updatedTodo);
    }
}
