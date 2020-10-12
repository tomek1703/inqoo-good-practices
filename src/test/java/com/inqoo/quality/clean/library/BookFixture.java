package com.inqoo.quality.clean.library;

public class BookFixture {
    Book paleBlueDot() {
        return new Book(ISBN.of("0-679-43841-6"), "Carl Sagan", "Pale Blue Dot");
    }
}