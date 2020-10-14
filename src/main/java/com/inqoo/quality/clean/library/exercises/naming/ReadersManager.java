package com.inqoo.quality.clean.library.exercises.naming;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

class ReadersManager {
    private final List<Reader> readers;

    ReadersManager() {
        readers = new ArrayList<>();
    }

    void enroll(Reader reader) {
        readers.add(reader);
    }

    List<Reader> readers() {
        return unmodifiableList(readers);
    }

    boolean contains(Reader reader) {
        return readers.contains(reader);
    }
}