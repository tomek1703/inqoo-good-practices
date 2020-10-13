package com.inqoo.quality.clean.library.referential;

import java.util.List;

public interface ReaderFacade {
    void enroll(Reader reader);

    List<Reader> enrolledReaders();
}
