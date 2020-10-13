package com.inqoo.quality.clean.library.referential;

import com.inqoo.quality.clean.library.referential.Book;
import com.inqoo.quality.clean.library.referential.ISBN;

class BookFixture {
    Book paleBlueDot() {
        return new Book(ISBN.of("0-679-43841-6"), "Carl Sagan", "Pale Blue Dot");
    }

    Book cleanCode() {
        return new Book(ISBN.of("978-0132350884"), "Robert C. Martin", "Clean Code");
    }
}