package com.inqoo.quality.clean.library.exercises.naming;

import java.util.List;

public interface ReaderFacade {
    void enroll(Reader reader);

    List<Reader> getReaders();
}
