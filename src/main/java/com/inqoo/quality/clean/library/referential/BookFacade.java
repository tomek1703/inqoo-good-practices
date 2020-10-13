package com.inqoo.quality.clean.library.referential;

import java.util.Set;

public interface BookFacade {
    void addBook(Book book);

    void addBooks(Book book, int amount);

    int availableCopies(Book book);

    Set<Book> bookCatalogue();

    int availableAmount(ISBN isbn);

    BorrowOutcome borrow(Book book, Reader reader);

    ReturnOutcome giveBack(Book book, Reader reader);
}
