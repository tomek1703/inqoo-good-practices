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
        if(readerNotRegistered(reader)) {
            return readerNotEnrolled;
        } else if (bookNotAvailable(book)) {
            return notInCatalogue;
        } else if (readerHasThisBook(book, reader)) {
            return bookAlreadyBorrowedByReader;
        } else if (notAvailableBookCopies(book)) {
            return noAvailableCopies;
        }
        else{books.take(book.getIsbn());
            borrowedBooksRegistry.rent(book, reader);
            return success;
        }
    }

    private boolean notAvailableBookCopies(Book book) {
        return books.availableCopies(book) == 0;
    }

    private boolean readerHasThisBook(Book book, Reader reader) {
        return borrowedBooksRegistry.readerHasBookCopy(book, reader);
    }

    private boolean bookNotAvailable(Book book) {
        return !books.contains(book);
    }

    private boolean readerNotRegistered(Reader reader) {
        return !readersRegistry.contains(reader);
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