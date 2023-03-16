package com.example.library;

import java.util.List;
import java.util.Comparator;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
class BookController {

  private final BookRepository repository;

  BookController(BookRepository repository) {
    this.repository = repository;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/books")
  List<Book> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/books")
  Book newBook(@RequestBody Book newBook) {
    return repository.save(newBook);
  }

  @PutMapping("/books/{id}")
  Book updateBook(@PathVariable String newTitle, @PathVariable Long id) {
    return repository.findById(id)
      .map(book -> {
        book.setAuthor(newTitle);
        return repository.save(book);
      })
      .orElseThrow(() -> new BookNotFoundException(id));
  }

  @PutMapping("/books")
  List<Book> sort(){
    List<Book> books = repository.findAll();
    books.sort(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle));
    return books;
  }

  @GetMapping("/books/search")
  Book searchByTitle(@RequestParam String title) {
    List<Book> books = repository.findAll();
    for (Book book : books) {
        if (book.getTitle().equals(title)) {
            return book;
        }
    }
    return null;
  }

  @DeleteMapping("/books/{id}")
  void deleteBook(@PathVariable Long id) {
    repository.deleteById(id);
  }
}