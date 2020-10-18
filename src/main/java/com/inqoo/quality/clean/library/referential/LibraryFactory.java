package com.inqoo.quality.clean.library.referential;

class LibraryFactory {
    Library library() {
        ReadersRegisterList readersRegister = new ReadersRegisterList();
        Books books = new Books(new Catalogue(), new BookWarehouse());
        BorrowedBooksRegistry borrowedBookRegistry = new BorrowedBooksRegistry();
        BookBorrow bookBorrow = new BookBorrow(books, readersRegister, borrowedBookRegistry);
        return new Library(readersRegister, bookBorrow, books);
    }
}
