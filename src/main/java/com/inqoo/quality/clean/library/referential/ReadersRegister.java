package com.inqoo.quality.clean.library.referential;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

class ReadersRegister {
    private final List<Reader> readers;

    ReadersRegister() {
        readers = new ArrayList<>();
    }

    void enroll(Reader reader) {
        readers.add(reader);
    }

    // TODO: 13/10/2020 add formatting to slides
    List<Reader> readers() {
        return unmodifiableList(readers);
    }

    boolean contains(Reader reader) {
        return readers.contains(reader);
    }
}