package com.inqoo.quality.clean.library.exercises.naming;

import org.junit.Test;

import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.bookAlreadyBorrowedByReader;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.noAvailableCopies;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.notInCatalogue;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.readerNotEnrolled;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.success;
import static org.assertj.core.api.Assertions.assertThat;

public class LibraryTest {

    private final LibraryManager libraryManager = new LibraryFactory().library();
    private final BookFixture bookFixture = new BookFixture();
    private final Book paleBlueDot = bookFixture.paleBlueDot();
    private final Book cleanCode = bookFixture.cleanCode();
    private final ReadersFixture readersFixture = new ReadersFixture();
    private final Reader johnSmith = readersFixture.johnSmith();
    private final Reader janeDoe = readersFixture.janeDoe();

    @Test
    public void addedBookIsVisibleInCatalogue() {
        // when
        addPaleBlueDotToLibrary();

        // then
        assertThat(libraryManager.getBooks()).contains(paleBlueDot);
    }

    @Test
    public void newReaderEnrolls() {
        // when
        enrollJonhSmith();

        // then
        assertThat(libraryManager.loadReaders()).contains(johnSmith);
    }

    @Test
    public void cannotBorrowBookWhichIsNotInCatalogue() {
        // given
        enrollJonhSmith();

        // when
        BorrowOutcome outcome = readerTriesToBorrowBook(paleBlueDot, johnSmith);

        // then
        assertThat(outcome).isEqualTo(notInCatalogue);
    }

    @Test
    public void notEnrolledReaderCannotBorrow() {
        // when
        BorrowOutcome outcome = readerTriesToBorrowBook(paleBlueDot, johnSmith);

        // then
        assertThat(outcome).isEqualTo(readerNotEnrolled);
    }

    @Test
    public void borrowABook() {
        // given
        addPaleBlueDotToLibrary();
        // and
        enrollJonhSmith();

        // when
        BorrowOutcome borrowOutcome = readerTriesToBorrowBook(paleBlueDot, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(BorrowOutcome.success);
        // and
        assertThat(libraryManager.fetchBookAmounts(paleBlueDot)).isEqualTo(0);
    }

    @Test
    public void cannotBorrowBookWhenNoCopiesAvailable() {
        // given
        addPaleBlueDotToLibrary();
        // and
        enrollJonhSmith();
        // and
        enrollJaneDoe();
        // and
        readerTriesToBorrowBook(paleBlueDot, johnSmith);

        // when
        BorrowOutcome outcome = readerTriesToBorrowBook(paleBlueDot, janeDoe);

        // then
        assertThat(outcome).isEqualTo(noAvailableCopies);
    }

    @Test
    public void seeSingleBookItemNumber() {
        // given
        addPaleBlueDotToLibrary();

        // when
        int amount = libraryManager.fetchBookAmounts(paleBlueDot);

        // then
        assertThat(amount).isEqualTo(1);
    }

    @Test
    public void canSeeMultipleBookCopies() {
        // given
        libraryManager.addBooks(paleBlueDot, 2);
        // and
        enrollJonhSmith();
        // and
        enrollJaneDoe();

        // when
        BorrowOutcome outcome = readerTriesToBorrowBook(paleBlueDot, johnSmith);

        // then
        assertThat(outcome).isEqualTo(success);

        // when
        outcome = readerTriesToBorrowBook(paleBlueDot, janeDoe);

        // then
        assertThat(outcome).isEqualTo(success);

        // when
        int amount = libraryManager.fetchBookAmounts(paleBlueDot);

        // then
        assertThat(amount).isEqualTo(0);
    }

    @Test
    public void oneBookCannotBeBorrowedTwiceBySameReader() {
        // given
        addPaleBlueDotToLibrary();
        // and
        enrollJonhSmith();

        // when
        BorrowOutcome borrowOutcome = readerTriesToBorrowBook(paleBlueDot, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(success);

        // when
        borrowOutcome = readerTriesToBorrowBook(paleBlueDot, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(bookAlreadyBorrowedByReader);
    }

    @Test
    public void bookIsReturned() {
        // given
        addPaleBlueDotToLibrary();
        // and
        enrollJonhSmith();

        // when
        BorrowOutcome borrowOutcome = readerTriesToBorrowBook(paleBlueDot, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(success);

        // when
        ReturnOutcome returnOutcome = libraryManager.returns(paleBlueDot, johnSmith);

        // then
        assertThat(returnOutcome).isEqualTo(ReturnOutcome.success);
    }

    @Test
    public void cannotReturnBookForNotEnrolledReader() {
        // given
        addPaleBlueDotToLibrary();

        // when
        ReturnOutcome returnOutcome = libraryManager.returns(paleBlueDot, johnSmith);

        // then
        assertThat(returnOutcome).isEqualTo(ReturnOutcome.readerNotEnrolled);
    }

    @Test
    public void cannotReturnBookThatIsNotInCatalogue() {
        // given
        enrollJonhSmith();

        // when
        ReturnOutcome returnOutcome = libraryManager.returns(paleBlueDot, johnSmith);

        // then
        assertThat(returnOutcome).isEqualTo(ReturnOutcome.notInCatalogue);
    }

    @Test
    public void cannotReturnBookThatWasNotBorrowedByReader() {
        // given
        enrollJonhSmith();
        // and
        addPaleBlueDotToLibrary();

        // when
        ReturnOutcome returnOutcome = libraryManager.returns(paleBlueDot, johnSmith);

        // then
        assertThat(returnOutcome).isEqualTo(ReturnOutcome.bookNotBorrowedByReader);
    }

    @Test
    public void readerCanBorrowTwoBooks() {
        // given
        enrollJonhSmith();
        // and
        addPaleBlueDotToLibrary();
        // and
        addCleanCodeToLibrary();

        // when
        BorrowOutcome borrowOutcome = readerTriesToBorrowBook(paleBlueDot, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(success);

        // when
        borrowOutcome = readerTriesToBorrowBook(cleanCode, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(success);
    }

    private void enrollJonhSmith() {
        libraryManager.newReader(johnSmith);
    }

    private void addPaleBlueDotToLibrary() {
        libraryManager.putBook(paleBlueDot);
    }

    private void addCleanCodeToLibrary() {
        libraryManager.putBook(cleanCode);
    }

    private BorrowOutcome readerTriesToBorrowBook(Book book, Reader reader) {
        return libraryManager.provideBook(book, reader);
    }

    private void enrollJaneDoe() {
        libraryManager.newReader(janeDoe);
    }
}