package com.inqoo.quality.clean.library.exercises.naming;

import java.util.List;

public interface ReaderFacade {
    void newReader(Reader reader);

    List<Reader> loadReaders();
}
