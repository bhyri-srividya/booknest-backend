package com.booknest.library.controller;

import com.booknest.library.entity.Book;
import com.booknest.library.entity.IssuedBook;
import com.booknest.library.repository.BookRepository;
import com.booknest.library.repository.IssuedBookRepository;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/issuedBooks")
@CrossOrigin(origins = "*") // ✅ IMPORTANT FIX
public class IssuedBookController {

    private final IssuedBookRepository issuedBookRepository;
    private final BookRepository bookRepository;

    public IssuedBookController(IssuedBookRepository issuedBookRepository,
                                BookRepository bookRepository) {
        this.issuedBookRepository = issuedBookRepository;
        this.bookRepository = bookRepository;
    }

    // ✅ ISSUE BOOK
    @PostMapping("/issue")
    public IssuedBook issueBook(@RequestBody Map<String, Object> data) {

        Long bookId = Long.valueOf(data.get("bookId").toString());
        String studentName = data.get("studentName").toString();
        String studentEmail = data.get("studentEmail").toString();
        int loanDays = Integer.parseInt(data.get("loanDays").toString());

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        IssuedBook issuedBook = new IssuedBook();

        issuedBook.setBookTitle(book.getTitle());
        issuedBook.setStudentName(studentName);
        issuedBook.setStudentEmail(studentEmail);

        LocalDate issueDate = LocalDate.now();
        issuedBook.setIssueDate(issueDate);
        issuedBook.setDueDate(issueDate.plusDays(loanDays));

        issuedBook.setStatus("ISSUED");

        return issuedBookRepository.save(issuedBook);
    }

    // ✅ RETURN BOOK (THIS IS YOUR ERROR PART)
    @PutMapping("/return/{id}")
    public IssuedBook returnBook(@PathVariable Long id) {

        IssuedBook book = issuedBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setStatus("RETURNED");

        return issuedBookRepository.save(book);
    }

    // ✅ GET ALL
    @GetMapping
    public List<IssuedBook> getAllIssuedBooks() {
        return issuedBookRepository.findAll();
    }
}