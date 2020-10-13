package com.inqoo.quality.clean.library.exercises.classes;

import static com.inqoo.quality.clean.library.exercises.classes.BorrowOutcome.bookAlreadyBorrowedByReader;
import static com.inqoo.quality.clean.library.exercises.classes.BorrowOutcome.noAvailableCopies;
import static com.inqoo.quality.clean.library.exercises.classes.BorrowOutcome.notInCatalogue;
import static com.inqoo.quality.clean.library.exercises.classes.BorrowOutcome.readerNotEnrolled;
import static com.inqoo.quality.clean.library.exercises.classes.BorrowOutcome.success;

class BookBorrow {
    private final Catalogue catalogue;
    private final BookWarehouse bookWarehouse;
    private final ReadersRegister readersRegister;
    private final BorrowedBooksRegistry borrowedBooksRegistry;

    BookBorrow(Catalogue catalogue, BookWarehouse bookWarehouse, ReadersRegister readersRegister, BorrowedBooksRegistry borrowedBookRegistry) {
        this.catalogue = catalogue;
        this.bookWarehouse = bookWarehouse;
        this.readersRegister = readersRegister;
        this.borrowedBooksRegistry = borrowedBookRegistry;
    }

    BorrowOutcome borrow(Book book, Reader reader) {
        if (readerNotEnrolled(reader)) {
            return readerNotEnrolled;
        }

        if (bookNotInCatalogue(book)) {
            return notInCatalogue;
        }

        if (readerAlreadyBorrowedTheBook(book, reader)) {
            return bookAlreadyBorrowedByReader;
        }

        if (noBookCopiesAvailable(book)) {
            return noAvailableCopies;
        }

        bookWarehouse.take(book.getIsbn());
        borrowedBooksRegistry.rent(book, reader);
        return success;
    }

    private boolean readerAlreadyBorrowedTheBook(Book book, Reader reader) {
        return borrowedBooksRegistry.readerHasBookCopy(book, reader);
    }

    private boolean noBookCopiesAvailable(Book book) {
        return bookWarehouse.availableCopiesAmount(book.getIsbn()) == 0;
    }

    private boolean bookNotInCatalogue(Book book) {
        return !catalogue.contains(book);
    }

    private boolean readerNotEnrolled(Reader reader) {
        return !readersRegister.contains(reader);
    }

    ReturnOutcome giveBack(Book book, Reader reader) {
        if (readerNotEnrolled(reader)) {
            return ReturnOutcome.readerNotEnrolled;
        }

        if (bookNotInCatalogue(book)) {
            return ReturnOutcome.notInCatalogue;
        }

        if (bookIsNotBorrowedByReader(book, reader)) {
            return ReturnOutcome.bookNotBorrowedByReader;
        }

        bookWarehouse.add(book.getIsbn());
        borrowedBooksRegistry.returnBook(book, reader);
        return ReturnOutcome.success;
    }

    private boolean bookIsNotBorrowedByReader(Book book, Reader reader) {
        return borrowedBooksRegistry.readerHasNoBookCopy(book, reader);
    }
}