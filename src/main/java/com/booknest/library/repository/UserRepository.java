package com.booknest.library.repository;

import com.booknest.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // This lets us find users by username
    Optional<User> findByUsername(String username);

    // This lets us find users by email
    Optional<User> findByEmail(String email);
}