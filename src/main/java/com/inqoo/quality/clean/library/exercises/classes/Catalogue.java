package com.inqoo.quality.clean.library.exercises.classes;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

class Catalogue {
    private final Set<Book> catalogue;

    Catalogue() {
        catalogue = new HashSet<>();
    }

    void add(Book book) {
        catalogue.add(book);
    }

    Set<Book> getAll() {
        return unmodifiableSet(catalogue);
    }

    boolean contains(Book book) {
        return catalogue.contains(book);
    }
}
