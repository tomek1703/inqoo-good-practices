package com.inqoo.quality.clean.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ReadersRegister {
    private final List<Reader> readers;

    public ReadersRegister() {
        readers = new ArrayList<>();
    }

    void enroll(Reader reader) {
        readers.add(reader);
    }

    List<Reader> readers() {
        return Collections.unmodifiableList(readers);
    }

    boolean contains(Reader reader) {
        return readers.contains(reader);
    }
}