package com.inqoo.quality.clean.library.exercises.naming;

import java.util.Set;

public interface BookFacade {
    void putBook(Book book);

    void addBooks(Book book, int amount);

    int fetchBookAmounts(Book book);

    Set<Book> getBooks();

    BorrowOutcome provideBook(Book book, Reader reader);

    ReturnOutcome returns(Book book, Reader reader);
}
