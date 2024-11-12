package com.lemonadesystems.bwalo.userservice.controller;

import com.lemonadesystems.bwalo.userservice.model.User;
import com.lemonadesystems.bwalo.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("User registration failed");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    // New endpoint to get all users
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        Optional<List<User>> users = Optional.ofNullable(userService.getAllUsers());
        if (users.isEmpty()) {
            return ResponseEntity.status(404).body("No users yet");  // or return a custom message
        }
        return ResponseEntity.ok(users);
    }
}
