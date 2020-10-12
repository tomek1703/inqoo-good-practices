package com.inqoo.quality.clean.library;

import java.util.List;
import java.util.Set;

import static com.inqoo.quality.clean.library.BorrowOutcome.bookAlreadyBorrowedByReader;
import static com.inqoo.quality.clean.library.BorrowOutcome.noAvailableCopies;
import static com.inqoo.quality.clean.library.BorrowOutcome.notInCatalogue;
import static com.inqoo.quality.clean.library.BorrowOutcome.readerNotEnrolled;
import static com.inqoo.quality.clean.library.BorrowOutcome.success;

public class Library {
    private final Catalogue catalogue;
    private final BookStore bookStore;
    private final ReadersRegister readersRegister;
    private final BookRentals bookRentals;

    public Library() {
        bookStore = new BookStore();
        catalogue = new Catalogue();
        readersRegister = new ReadersRegister();
        bookRentals = new BookRentals();
    }

    void addBook(Book book) {
        catalogue.add(book);
        bookStore.add(book.getIsbn());
    }

    void addBooks(Book book, int amount) {
        catalogue.add(book);
        bookStore.add(book.getIsbn(), amount);
    }

    int availableCopies(Book book) {
        return bookStore.get(book.getIsbn());
    }

    Set<Book> getAll() {
        return catalogue.getAll();
    }

    void enroll(Reader reader) {
        readersRegister.enroll(reader);
    }

    List<Reader> readers() {
        return readersRegister.readers();
    }

    BorrowOutcome borrow(Book book, Reader reader) {
        if (!readersRegister.contains(reader)) {
            return readerNotEnrolled;
        }

        if (!catalogue.contains(book)) {
            return notInCatalogue;
        }

        if (bookRentals.readerHasNoBookCopy(book, reader)) {
            return bookAlreadyBorrowedByReader;
        }

        if (bookStore.get(book.getIsbn()) == 0) {
            return noAvailableCopies;
        }

        bookStore.remove(book.getIsbn());
        bookRentals.register(book, reader);
        return success;
    }

    int availableAmont(ISBN isbn) {
        return bookStore.get(isbn);
    }

    ReturnOutcome giveBack(Book book, Reader reader) {
        if (!readersRegister.contains(reader)) {
            return ReturnOutcome.readerNotEnrolled;
        }

        if (!catalogue.contains(book)) {
            return ReturnOutcome.notInCatalogue;
        }

        if (bookRentals.readerHasNoBookCopy(book, reader)) {
            return ReturnOutcome.bookNotBorrowedByReader;
        }

        bookStore.add(book.getIsbn());
        bookRentals.unRegister(book, reader);
        return ReturnOutcome.success;
    }
}
