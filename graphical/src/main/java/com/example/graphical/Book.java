package com.example.graphical;

import java.util.Arrays;
import java.util.List;

public record Book(String id, String name, int pageCount, String authorId) {

    private static final List<Book> books = Arrays.asList(
            new Book("book-1", "Effective Java", 100, "author-1"),
            new Book("book-2", "Java 8", 200, "author-2"),
            new Book("book-3", "Java 9", 300, "author-3")
    );

    public static Book getById(String id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}