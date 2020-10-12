package com.inqoo.quality.clean.library;

import java.util.HashMap;
import java.util.Map;

class BookStore {
    private final Map<ISBN, Integer> bookStore;

    BookStore() {
        bookStore = new HashMap<>();
    }

    void add(ISBN isbn) {
        bookStore.merge(isbn, 1, Integer::sum);
    }

    void add(ISBN isbn, int amount) {
        bookStore.merge(isbn, amount, Integer::sum);
    }

    int get(ISBN isbn) {
        return bookStore.get(isbn);
    }

    void remove(ISBN isbn) {
        bookStore.put(isbn, bookStore.get(isbn) - 1);
    }
}
