package com.inqoo.quality.clean.library.referential;

import com.inqoo.quality.clean.library.referential.Reader;

class ReadersFixture {
    Reader johnSmith() {
        return new Reader("John Smith");
    }

    Reader janeDoe() {
        return new Reader("Jane Doe");
    }
}