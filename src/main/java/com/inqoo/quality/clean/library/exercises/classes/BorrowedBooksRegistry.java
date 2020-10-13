package com.inqoo.quality.clean.library.exercises.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BorrowedBooksRegistry {
    private final Map<Reader, List<ISBN>> rentals;

    BorrowedBooksRegistry() {
        rentals = new HashMap<>();
    }

    // TODO: 13/10/2020 jak chłopi na wsi
    void rent(Book book, Reader reader) {
        if (rentals.containsKey(reader)) {
            rentals.get(reader).add(book.getIsbn());
        } else {
            List<ISBN> books = new ArrayList<ISBN>();
            books.add(book.getIsbn());
            rentals.put(reader, books);
        }
    }

    boolean readerHasNoBookCopy(Book book, Reader reader) {
        return !readerHasBookCopy(book, reader);
    }

    // TODO: 13/10/2020 jak chłopi na wsi
    void returnBook(Book book, Reader reader) {
        rentals.get(reader).remove(book.getIsbn());
    }

    boolean readerHasBookCopy(Book book, Reader reader) {
        return rentals.containsKey(reader) && rentals.get(reader).contains(book.getIsbn());
    }
}