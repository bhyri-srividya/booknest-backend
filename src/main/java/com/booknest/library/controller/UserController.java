package com.booknest.library.controller;

import com.booknest.library.entity.User;
import com.booknest.library.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")  // ✅ FIXED (allow all ports)
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {

        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            return Collections.singletonMap("message", "Username already exists");
        }

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return Collections.singletonMap("message", "Email already exists");
        }

        userRepository.save(user);
        return Collections.singletonMap("message", "User registered successfully");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<User> userOpt = userRepository.findByUsername(username);

        if(userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return Collections.singletonMap("message", "Login successful");
        } else {
            return Collections.singletonMap("message", "Invalid username or password");
        }
    }
}