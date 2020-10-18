package com.inqoo.quality.clean.library.referential;

import java.util.List;

public interface ReadersRegister {
    List<Reader> readers();

    void enroll(Reader reader);
}
