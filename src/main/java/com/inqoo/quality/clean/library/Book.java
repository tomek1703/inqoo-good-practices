package com.inqoo.quality.clean.library;

public class Book {
    private final String isbn;
    private final String author;
    private final String title;

    public Book(String isbn, String author, String title) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }

    String getIsbn() {
        return isbn;
    }
}
