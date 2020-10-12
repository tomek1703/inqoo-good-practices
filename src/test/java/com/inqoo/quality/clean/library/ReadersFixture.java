package com.inqoo.quality.clean.library;

public class ReadersFixture {
    public ReadersFixture() {
    }

    Reader johnSmith() {
        return new Reader("John Smith");
    }

    Reader janeDoe() {
        return new Reader("Jane Doe");
    }
}