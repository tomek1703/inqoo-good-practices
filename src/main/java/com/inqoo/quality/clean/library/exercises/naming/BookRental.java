package com.inqoo.quality.clean.library.exercises.naming;

// TODO: clean up methods

import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.bookAlreadyBorrowedByReader;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.noAvailableCopies;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.notInCatalogue;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.readerNotEnrolled;
import static com.inqoo.quality.clean.library.exercises.naming.BorrowOutcome.success;

class BookRental {
    private final Books books;
    private final Readers readersRegistry;
    private final BorrowedBooksRegistry borrowedBooksRegistry;

    BookRental(Books books, Readers readers, BorrowedBooksRegistry borrowedBookRegistry) {
        this.books = books;
        this.readersRegistry = readers;
        this.borrowedBooksRegistry = borrowedBookRegistry;
    }

    BorrowOutcome borrow(Book book, Reader reader) {

        if (readersRegistry.contains(reader) &&
                books.contains(book) &&
                !borrowedBooksRegistry.readerHasBookCopy(book, reader) &&
                books.availableCopies(book) > 0
        ) {
            books.take(book.getIsbn());
            borrowedBooksRegistry.rent(book, reader);
            return success;
        } else
        if (!readersRegistry.contains(reader)) {
            return readerNotEnrolled;
        } else if (!books.contains(book)) {
            return notInCatalogue;
        } else if (borrowedBooksRegistry.readerHasBookCopy(book, reader)) {
            return bookAlreadyBorrowedByReader;
        } else if (books.availableCopies(book) == 0) {
            return noAvailableCopies;
        }
        return null;
    }

    ReturnOutcome giveBack(Book book, Reader reader) {
        if (!readersRegistry.contains(reader)){
            return ReturnOutcome.readerNotEnrolled;
        }

        if (!books.contains(book)){
            return ReturnOutcome.notInCatalogue;
        }

        if (borrowedBooksRegistry.readerHasNoBookCopy(book, reader)){
            return ReturnOutcome.bookNotBorrowedByReader;
        }

        books.add(book.getIsbn());
        borrowedBooksRegistry.returnBook(book, reader);
        return ReturnOutcome.success;
    }

}