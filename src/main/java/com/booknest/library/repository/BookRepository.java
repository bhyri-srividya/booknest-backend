package com.booknest.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booknest.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}