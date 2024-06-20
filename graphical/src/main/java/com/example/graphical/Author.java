package com.example.graphical;

import java.util.Arrays;
import java.util.List;

public record Author (String id, String firstName, String lastName) {
    private static final List<Author> authors = Arrays.asList(
            new Author("author-1","Aditya","Bhambere"),
            new Author("author-2","Abhijeet","Bhambere"),
            new Author("author-3","Aranka","Bhambere")
    );

    public static Author getById(String id) {
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst()
                .orElse(null);

    }
}