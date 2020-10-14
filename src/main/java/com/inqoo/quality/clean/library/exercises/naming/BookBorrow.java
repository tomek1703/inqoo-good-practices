package com.inqoo.quality.clean.library.exercises.naming;

// TODO: clean up methods

import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.bookAlreadyBorrowedByReader;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.noAvailableCopies;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.notInCatalogue;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.readerNotEnrolled;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.success;

class BookBorrow {
    private final Books books;
    private final ReadersRegister readersRegister;
    private final BorrowedBooksRegistry borrowedBooksRegistry;

    BookBorrow(Books books, ReadersRegister readersRegister, BorrowedBooksRegistry borrowedBookRegistry) {
        this.books = books;
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

        books.take(book.getIsbn());
        borrowedBooksRegistry.rent(book, reader);
        return success;
    }

    private boolean readerAlreadyBorrowedTheBook(Book book, Reader reader) {
        return borrowedBooksRegistry.readerHasBookCopy(book, reader);
    }

    private boolean noBookCopiesAvailable(Book book) {
        return books.availableCopies(book) == 0;
    }

    private boolean bookNotInCatalogue(Book book) {
        return !books.contains(book);
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

        books.add(book.getIsbn());
        borrowedBooksRegistry.returnBook(book, reader);
        return ReturnOutcome.success;
    }

    private boolean bookIsNotBorrowedByReader(Book book, Reader reader) {
        return borrowedBooksRegistry.readerHasNoBookCopy(book, reader);
    }
}