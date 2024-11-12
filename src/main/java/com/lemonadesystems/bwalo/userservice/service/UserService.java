package com.lemonadesystems.bwalo.userservice.service;

import com.lemonadesystems.bwalo.userservice.model.User;
import com.lemonadesystems.bwalo.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        try {
            // Save user to the database
            return userRepository.save(user); // This will persist the user
        } catch (Exception e) {
            // Log the error if saving fails
            throw new RuntimeException("Error saving user", e);
        }
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();  // JPA method to get all users
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
