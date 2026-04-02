package com.booknest.library.controller;

import org.springframework.web.bind.annotation.*;
import com.booknest.library.repository.BookRepository;
import com.booknest.library.entity.Book;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")

public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // GET all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ADD book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // DELETE book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    // UPDATE book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {

        Book book = bookRepository.findById(id).orElseThrow();

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());

        return bookRepository.save(book);
    }
}