package com.inqoo.quality.clean.library.exercises.naming;

import java.util.Set;

public interface BookFacade {
    void addBook(Book book);

    void addBooks(Book book, int amount);

    int showAvailableCopies(Book book);

    Set<Book> getBooks();

    BorrowOutcome borrowBook(Book book, Reader reader);

    ReturnOutcome returnBook(Book book, Reader reader);
}
