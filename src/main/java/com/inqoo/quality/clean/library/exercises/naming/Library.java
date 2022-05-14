package com.inqoo.quality.clean.library.exercises.naming;

import java.util.List;
import java.util.Set;

public class Library implements BookFacade, ReaderFacade {
    private final Readers readers;
    private final BookRental bookRental;
    private final Books books;

    Library(Readers readers, BookRental bookRental, Books books) {
        this.readers = readers;
        this.bookRental = bookRental;
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
    public int showAvailableCopies(Book book) {
        return books.availableCopies(book);
    }

    @Override
    public Set<Book> getBooks() {
        return books.bookCatalogue();
    }

    @Override
    public void enroll(Reader reader) {
        readers.enroll(reader);
    }

    @Override
    public List<Reader> getReaders() {
        return readers.readers();
    }

    @Override
    public BorrowOutcome borrowBook(Book book, Reader reader) {
        return bookRental.borrow(book, reader);
    }

    @Override
    public ReturnOutcome returnBook(Book book, Reader reader) {
        return bookRental.giveBack(book, reader);
    }
}
