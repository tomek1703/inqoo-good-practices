package com.inqoo.quality.clean.library.referential;

import java.util.List;
import java.util.Set;

// TODO: 13/10/2020 naming exercise
// TODO: 13/10/2020 method exercise
// TODO: 13/10/2020 classes exercise

class Library {
    private final ReadersRegister readersRegister;
    private final BookBorrow bookBorrow;
    private final Books books;

    Library(ReadersRegister readersRegister, BookBorrow bookBorrow, Books books) {
        this.readersRegister = readersRegister;
        this.bookBorrow = bookBorrow;
        this.books = books;
    }

    void addBook(Book book) {
        books.addBook(book);
    }

    void addBooks(Book book, int amount) {
        books.addBooks(book, amount);
    }

    int availableCopies(Book book) {
        return books.availableCopies(book);
    }

    Set<Book> bookCatalogue() {
        return books.bookCatalogue();
    }

    void enroll(Reader reader) {
        readersRegister.enroll(reader);
    }

    List<Reader> enrolledReaders() {
        return readersRegister.readers();
    }

    int availableAmont(ISBN isbn) {
        return books.availableCopiesAmount(isbn);
    }

    BorrowOutcome borrow(Book book, Reader reader) {
        return bookBorrow.borrow(book, reader);
    }

    ReturnOutcome giveBack(Book book, Reader reader) {
        return bookBorrow.giveBack(book, reader);
    }
}
