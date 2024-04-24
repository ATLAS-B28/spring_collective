package com.example.todo.controller;

import com.example.todo.dto.TodoDto;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todos/")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    /*
    * Annotation for specifying a method access-control expression
    * which will be evaluated to decide whether a method invocation
    * is allowed or not.
    * */
    @PostMapping("create-todo")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.addTodo(todoDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("get/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/all")
    public ResponseEntity<List<TodoDto>> getAllTodo() {
        //return new ResponseEntity<>(todoService.getAllTodo(), HttpStatus.FOUND);
        return ResponseEntity.ok(todoService.getAllTodo());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        //return new ResponseEntity<>(todoService.deleteTodo(id), HttpStatus.OK);
        return ResponseEntity.ok("Todo deleted successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")//put sends the whole body in the request
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        //return new ResponseEntity<>(todoService.updateTodo(id, todoDto), HttpStatus.OK);
        return ResponseEntity.ok(todoService.updateTodo(id, todoDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/update-status/{id}")//here a patch used as we update
    // only one aspect
    //of resource and so send only that part of resource
    public ResponseEntity<TodoDto> updateTodoStatus(@PathVariable Long id) {
        //return new ResponseEntity<>(todoService.completeTodo(id), HttpStatus.OK);
        return ResponseEntity.ok(todoService.completeTodo(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/update-status-incomplete/{id}")
    public ResponseEntity<TodoDto> updateTodoStatusInComplete(@PathVariable Long id) {
        //return new ResponseEntity<>(todoService.inCompleteTodo(id), HttpStatus.OK);
        return ResponseEntity.ok(todoService.inCompleteTodo(id));
    }
}
