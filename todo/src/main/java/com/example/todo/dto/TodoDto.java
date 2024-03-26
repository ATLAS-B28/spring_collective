package com.example.todo.dto;

public record TodoDto(
        Long id,
        String title,
        String description,
        boolean completed
) {}
