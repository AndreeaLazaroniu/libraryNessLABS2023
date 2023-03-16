package com.example.library;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {
    private @Id @GeneratedValue Long id;
    private String title;
    private String author;

    Book() {}
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    public Long getId() {
      return this.id;
    }

    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setId(Long id) {
      this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
      this.title = title;
  }

    @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Book))
      return false;
      Book book = (Book) o;
    return Objects.equals(this.title, book.title) && Objects.equals(this.author, book.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash( this.title, this.author);
  }

  @Override
  public String toString() {
    return "Book{" + "title='" + this.title + '\'' + ", author='" + this.author + '\'' + '}';
  }
}
