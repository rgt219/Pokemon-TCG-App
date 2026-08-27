package com.poketome.useridentityservice.service;

import com.poketome.useridentityservice.dto.UserLoginRequest;
import com.poketome.useridentityservice.dto.UserRegistrationRequest;
import com.poketome.useridentityservice.model.User;
import com.poketome.useridentityservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    // Initialize the official BCrypt password encoder
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public User registerUser(UserRegistrationRequest request) {
        // 1. Business Rule: Check if the username is already taken
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // 2. Business Rule: Check if the email is already taken
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }

        // 3. Transformation & Security: Hash the raw password securely
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());

        // This takes "MySecretPassword123" and turns it into something like
        // "$2a$10$X7...[scrambled]"
        String secureHash = passwordEncoder.encode(request.getPassword());
        newUser.setPasswordHash(secureHash);

        // 4. Persistence: Save to the database via the Repository
        return userRepository.save(newUser);
    }

    public String authenticateUser(UserLoginRequest request) {
        // 1. Find the user in the database
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        // 2. Verify the raw password against the stored BCrypt hash
        boolean isMatch = passwordEncoder.matches(request.getPassword(), user.getPasswordHash());
        if (!isMatch) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // 3. Credentials are valid! Mint and return a signed JWT token
        return jwtService.generateToken(user.getUsername());
    }
}