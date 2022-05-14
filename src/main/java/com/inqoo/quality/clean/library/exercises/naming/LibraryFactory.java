package com.inqoo.quality.clean.library.exercises.naming;

class LibraryFactory {
    Library library() {
        Readers readers = new Readers();
        Books books = new Books(new Catalogue(), new BookWarehouse());
        BorrowedBooksRegistry borrowedBookRegistry = new BorrowedBooksRegistry();
        BookRental bookRental = new BookRental(books, readers, borrowedBookRegistry);
        return new Library(readers, bookRental, books);
    }
}
