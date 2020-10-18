package com.inqoo.quality.clean.library.exercises.naming;

import java.util.Map;
import java.util.Set;

// TODO: class cleanup

class LibraryResources {
    private final Catalogue catalogue;
    private final BookWarehouse bookWarehouse;

    LibraryResources(Catalogue catalogue, BookWarehouse bookWarehouse) {
        this.catalogue = catalogue;
        this.bookWarehouse = bookWarehouse;
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

    void take(ISBN isbn) {
        bookWarehouse.take(isbn);
    }

    boolean contains(Book book) {
        return catalogue.contains(book);
    }

    void add(ISBN isbn) {
        bookWarehouse.add(isbn);
    }
}