package com.inqoo.quality.clean.library.exercises.naming;

import java.util.HashMap;
import java.util.Map;

class BookWarehouse {
    private final Map<ISBN, Integer> bookStore;

    BookWarehouse() {
        bookStore = new HashMap<>();
    }

    void add(ISBN isbn) {
        bookStore.merge(isbn, 1, Integer::sum);
    }

    void add(ISBN isbn, int amount) {
        bookStore.merge(isbn, amount, Integer::sum);
    }

    int availableCopiesAmount(ISBN isbn) {
        return bookStore.get(isbn);
    }

    void take(ISBN isbn) {
        bookStore.put(isbn, bookStore.get(isbn) - 1);
    }
}
