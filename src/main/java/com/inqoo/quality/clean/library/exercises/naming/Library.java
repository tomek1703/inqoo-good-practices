package com.inqoo.quality.clean.library.exercises.naming;

import java.util.List;
import java.util.Set;

class Library {
    private final Catalogue catalogue;
    private final BookWarehouse bookWarehouse;
    private final ReadersRegister readersRegister;
    private final BookBorrow bookBorrow;

    Library() {
        bookWarehouse = new BookWarehouse();
        catalogue = new Catalogue();
        readersRegister = new ReadersRegister();
        BorrowedBooksRegistry borrowedBookRegistry = new BorrowedBooksRegistry();
        bookBorrow = new BookBorrow(catalogue, bookWarehouse, readersRegister, borrowedBookRegistry);
    }

    void addBook(Book book) {
        catalogue.add(book);
        bookWarehouse.add(book.getIsbn());
    }

    void addBooks(Book book, int amount) {
        catalogue.add(book);
        bookWarehouse.add(book.getIsbn(), amount);
    }

    int availableCopies(Book book) {
        return bookWarehouse.availableCopiesAmount(book.getIsbn());
    }

    Set<Book> bookCatalogue() {
        return catalogue.getAll();
    }

    void enroll(Reader reader) {
        readersRegister.enroll(reader);
    }

    List<Reader> enrolledReaders() {
        return readersRegister.readers();
    }

    int availableAmont(ISBN isbn) {
        return bookWarehouse.availableCopiesAmount(isbn);
    }

    BorrowOutcome borrow(Book book, Reader reader) {
        return bookBorrow.borrow(book, reader);
    }

    ReturnOutcome giveBack(Book book, Reader reader) {
        return bookBorrow.giveBack(book, reader);
    }
}
