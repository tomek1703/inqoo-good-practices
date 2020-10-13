package com.inqoo.quality.clean.library.exercises.naming;

import org.junit.Test;

import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.bookAlreadyBorrowedByReader;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.noAvailableCopies;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.notInCatalogue;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.readerNotEnrolled;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.success;
import static org.assertj.core.api.Assertions.assertThat;

public class LibraryTest {

    private final com.inqoo.quality.clean.library.exercises.naming.Library library = new com.inqoo.quality.clean.library.exercises.naming.Library();
    private final BookFixture bookFixture = new BookFixture();
    private final com.inqoo.quality.clean.library.exercises.naming.Book paleBlueDot = bookFixture.paleBlueDot();
    private final com.inqoo.quality.clean.library.exercises.naming.Book cleanCode = bookFixture.cleanCode();
    private final ReadersFixture readersFixture = new ReadersFixture();
    private final com.inqoo.quality.clean.library.exercises.naming.Reader johnSmith = readersFixture.johnSmith();
    private final com.inqoo.quality.clean.library.exercises.naming.Reader janeDoe = readersFixture.janeDoe();

    @Test
    public void bookIsVisibleInCatalogue() {
        // when
        addPaleBlueDotToLibrary();

        // then
        assertThat(library.bookCatalogue()).contains(paleBlueDot);
    }

    @Test
    public void newReaderEnrolls() {
        // when
        enrollJonhSmith();

        // then
        assertThat(library.enrolledReaders()).contains(johnSmith);
    }

    @Test
    public void cannotBorrowBookNotInCatalogue() {
        // given
        enrollJonhSmith();

        // when
        BorrowOutcome outcome = library.borrow(paleBlueDot, johnSmith);

        // then
        assertThat(outcome).isEqualTo(notInCatalogue);
    }

    @Test
    public void notEnrolledReaderCannotBorrow() {
        // when
        BorrowOutcome outcome = library.borrow(paleBlueDot, johnSmith);

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
        BorrowOutcome borrowOutcome = library.borrow(paleBlueDot, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(BorrowOutcome.success);
        // and
        assertThat(library.availableAmont(paleBlueDot.getIsbn())).isEqualTo(0);
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
        library.borrow(paleBlueDot, johnSmith);

        // when
        BorrowOutcome outcome = library.borrow(paleBlueDot, janeDoe);

        // then
        assertThat(outcome).isEqualTo(noAvailableCopies);
    }

    private void enrollJaneDoe() {
        library.enroll(janeDoe);
    }

    @Test
    public void seeSingleBookItemNumber() {
        // given
        addPaleBlueDotToLibrary();

        // when
        int amount = library.availableCopies(paleBlueDot);

        // then
        assertThat(amount).isEqualTo(1);
    }

    @Test
    public void canSeeMultipleBookCopies() {
        // given
        library.addBooks(paleBlueDot, 2);
        // and
        enrollJonhSmith();
        // and
        enrollJaneDoe();

        // when
        BorrowOutcome outcome = library.borrow(paleBlueDot, johnSmith);

        // then
        assertThat(outcome).isEqualTo(success);

        // when
        outcome = library.borrow(paleBlueDot, janeDoe);

        // then
        assertThat(outcome).isEqualTo(success);

        // when
        int amount = library.availableCopies(paleBlueDot);

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
        BorrowOutcome borrowOutcome = library.borrow(paleBlueDot, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(success);

        // when
        borrowOutcome = library.borrow(paleBlueDot, johnSmith);

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
        BorrowOutcome borrowOutcome = library.borrow(paleBlueDot, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(success);

        // when
        ReturnOutcome returnOutcome = library.giveBack(paleBlueDot, johnSmith);

        // then
        assertThat(returnOutcome).isEqualTo(ReturnOutcome.success);
    }

    @Test
    public void cannotReturnBookForNotEnrolledReader() {
        // given
        addPaleBlueDotToLibrary();

        // when
        ReturnOutcome returnOutcome = library.giveBack(paleBlueDot, johnSmith);

        // then
        assertThat(returnOutcome).isEqualTo(ReturnOutcome.readerNotEnrolled);
    }

    @Test
    public void cannotReturnBookThatIsNotInCatalogue() {
        // given
        enrollJonhSmith();

        // when
        ReturnOutcome returnOutcome = library.giveBack(paleBlueDot, johnSmith);

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
        ReturnOutcome returnOutcome = library.giveBack(paleBlueDot, johnSmith);

        // then
        assertThat(returnOutcome).isEqualTo(ReturnOutcome.bookNotBorrowedByReader);
    }

    @Test
    public void readerCanBorrowTwoBooks () {
        // given
        enrollJonhSmith();
        // and
        addPaleBlueDotToLibrary();
        // and
        addCleanCodeToLibrary();

        // when
        BorrowOutcome borrowOutcome = library.borrow(paleBlueDot, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(success);

        // when
        borrowOutcome = library.borrow(cleanCode, johnSmith);

        // then
        assertThat(borrowOutcome).isEqualTo(success);
    }

    private void enrollJonhSmith() {
        library.enroll(johnSmith);
    }

    private void addPaleBlueDotToLibrary() {
        library.addBook(paleBlueDot);
    }

    private void addCleanCodeToLibrary() {
        library.addBook(cleanCode);
    }
}
