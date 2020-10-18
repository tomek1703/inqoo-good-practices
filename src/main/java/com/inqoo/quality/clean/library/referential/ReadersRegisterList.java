package com.inqoo.quality.clean.library.referential;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

class ReadersRegisterList implements ReadersRegister {
    private final List<Reader> readers;

    ReadersRegisterList() {
        readers = new ArrayList<>();
    }

    public void enroll(Reader reader) {
        readers.add(reader);
    }

    // TODO: 13/10/2020 add formatting to slides
    public List<Reader> readers() {
        return unmodifiableList(readers);
    }

    boolean contains(Reader reader) {
        return readers.contains(reader);
    }
}