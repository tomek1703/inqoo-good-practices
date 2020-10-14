package com.inqoo.quality.clean.library.exercises.naming;

class LibraryFactory {
    Library library() {
        ReadersRegister readersRegister = new ReadersRegister();
        Books books = new Books(new Catalogue(), new BookWarehouse());
        BorrowedBooksRegistry borrowedBookRegistry = new BorrowedBooksRegistry();
        BookBorrow bookBorrow = new BookBorrow(books, readersRegister, borrowedBookRegistry);
        return new Library(readersRegister, bookBorrow, books);
    }
}
