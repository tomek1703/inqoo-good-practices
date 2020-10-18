package com.inqoo.quality.clean.library.referential;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadersRegisterSet implements ReadersRegister {
    private final Set<Reader> readers = new HashSet<>();

    @Override
    public List<Reader> readers() {
        return new ArrayList<>(readers);
    }

    @Override
    public void enroll(Reader reader) {
        readers.add(reader);
    }
}
