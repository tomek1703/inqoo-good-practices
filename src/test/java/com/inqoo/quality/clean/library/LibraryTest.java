package com.inqoo.quality.clean.library;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LibraryTest {

    @Test
    public void bookIsAvailableAfterAddingToLibrary() {
        // given
        Library library = new Library();
        // and
        Book paleBlueDot = new Book("0-679-43841-6", "Carl Sagan", "Pale Blue Dot");

        // when
        library.addBook(paleBlueDot);

        // then
        assertThat(library.all()).contains(paleBlueDot);
    }

    @Test
    public void newReaderEnrolls() {
        // given
        Library library = new Library();
        // and
        Reader reader = new Reader("John Smith");

        // when
        library.enroll(reader);

        // then
        assertThat(library.readers()).contains(reader);
    }

    @Test
    public void borrowABook() {
        // given
        Library library = new Library();
        // and
        Book paleBlueDot = new Book("0-679-43841-6", "Carl Sagan", "Pale Blue Dot");
        // and
        Reader reader = new Reader("John Smith");
        // and
        library.addBook(paleBlueDot);
        // and
        library.enroll(reader);

        // when
        boolean isSuccessful = library.borrow(paleBlueDot, reader);

        // then
        assertThat(isSuccessful).isTrue();

        // when
        isSuccessful = library.borrow(paleBlueDot, reader);

        // then
        assertThat(isSuccessful).isFalse();
    }
}
