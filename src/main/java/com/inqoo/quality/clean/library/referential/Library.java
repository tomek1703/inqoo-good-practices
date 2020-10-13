package com.inqoo.quality.clean.library.referential;

import java.util.List;
import java.util.Set;

// TODO: 13/10/2020 naming exercise
// TODO: 13/10/2020 method exercise
// TODO: 13/10/2020 classes exercise

public class Library implements BookFacade, ReaderFacade {
    private final ReadersRegister readersRegister;
    private final BookBorrow bookBorrow;
    private final Books books;

    public Library(ReadersRegister readersRegister, BookBorrow bookBorrow, Books books) {
        this.readersRegister = readersRegister;
        this.bookBorrow = bookBorrow;
        this.books = books;
    }

    @Override
    public void addBook(Book book) {
        books.addBook(book);
    }

    @Override
    public void addBooks(Book book, int amount) {
        books.addBooks(book, amount);
    }

    @Override
    public int availableCopies(Book book) {
        return books.availableCopies(book);
    }

    @Override
    public Set<Book> bookCatalogue() {
        return books.bookCatalogue();
    }

    @Override
    public void enroll(Reader reader) {
        readersRegister.enroll(reader);
    }

    @Override
    public List<Reader> enrolledReaders() {
        return readersRegister.readers();
    }

    @Override
    public int availableAmount(ISBN isbn) {
        return books.availableCopiesAmount(isbn);
    }

    @Override
    public BorrowOutcome borrow(Book book, Reader reader) {
        return bookBorrow.borrow(book, reader);
    }

    @Override
    public ReturnOutcome giveBack(Book book, Reader reader) {
        return bookBorrow.giveBack(book, reader);
    }
}
