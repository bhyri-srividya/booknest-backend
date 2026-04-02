package com.booknest.library.repository;

import com.booknest.library.entity.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {

}