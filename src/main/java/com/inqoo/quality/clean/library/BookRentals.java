package com.inqoo.quality.clean.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BookRentals {
    private final Map<Reader, List<ISBN>> rentals;

    public BookRentals() {
        rentals = new HashMap<>();
    }

    void register(Book book, Reader reader) {
        if (rentals.containsKey(reader)) {
            rentals.get(reader).add(book.getIsbn());
        } else {
            List<ISBN> books = new ArrayList<>();
            books.add(book.getIsbn());
            rentals.put(reader, books);
        }
    }

    boolean readerHasNoBookCopy(Book book, Reader reader) {
        return rentals.containsKey(reader) && rentals.get(reader).contains(book.getIsbn());
    }

    void unRegister(Book book, Reader reader) {
        rentals.get(reader).remove(book.getIsbn());
    }
}