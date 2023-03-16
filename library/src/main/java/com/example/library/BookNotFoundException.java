package com.example.library;

class BookNotFoundException extends RuntimeException {

    BookNotFoundException(Long id) {
      super("Could not find book " + id);
    }
  }
