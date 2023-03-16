package com.example.library;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    public List<Book> getAllBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void deleteBook(Book book) {
        books.remove(book);
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void updateBookAuthor(Book book, String author) {
        book.setAuthor(author);
    }

    public List<Book> getAllBooksSortedByAuthorAndTitle() {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle));
        return sortedBooks;
    }
    
}