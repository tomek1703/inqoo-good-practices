package com.inqoo.quality.clean.library;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;

public class Library {
    private final Set<Book> catalogue = new HashSet<>();
    private final Map<String, Integer> bookStore = new HashMap<>();
    private final List<Reader> readers = new ArrayList<>();

    void addBook(Book book) {
        catalogue.add(book);
        bookStore.merge(book.getIsbn(), 1, Integer::sum);
    }

    Set<Book> all() {
        return unmodifiableSet(catalogue);
    }

    void enroll(Reader reader) {
        readers.add(reader);
    }

    List<Reader> readers() {
        return unmodifiableList(readers);
    }

    boolean borrow(Book book, Reader reader) {
        if(!catalogue.contains(book)) {
            return false;
        }

        if(!readers.contains(reader)) {
            return false;
        }

        if(bookStore.get(book.getIsbn()) == 0) {
            return false;
        }

        bookStore.put(book.getIsbn(), bookStore.get(book.getIsbn())-1);
        return true;
    }
}
