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
        catalogue.getCatalogue().add(book);
        Map<ISBN, Integer> bookStore = bookWarehouse.getBookStore();
        if (bookStore.containsKey(book.getIsbn())) {
            int amount = bookStore.get(book.getIsbn());
            bookStore.put(book.getIsbn(), amount);
        } else {
            bookStore.put(book.getIsbn(), 1);
        }
    }

    void addBooks(Book book, int amount) {
        catalogue.getCatalogue().add(book);
        bookWarehouse.add(book.getIsbn(), amount);
    }

    int availableCopies(Book book) {
        return bookWarehouse.getBookStore().getOrDefault(book.getIsbn(), 0);
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