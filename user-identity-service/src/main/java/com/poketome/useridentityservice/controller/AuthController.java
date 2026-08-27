package com.poketome.useridentityservice.controller;

import com.poketome.useridentityservice.dto.UserLoginRequest;
import com.poketome.useridentityservice.dto.UserRegistrationRequest;
import com.poketome.useridentityservice.model.User;
import com.poketome.useridentityservice.service.AuthService;
import com.poketome.useridentityservice.service.JwtService;

import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        try {
            User registeredUser = authService.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered successfully: " + registeredUser.getUsername());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequest request) {
        try {

            String token = authService.authenticateUser(request);
            return ResponseEntity.ok(Map.of("token", token));

        } catch (IllegalArgumentException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());

        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Principal principal) {

        return ResponseEntity.ok(Map.of(
                "message", "Welcome to your secure profile, " + principal.getName() + "!",
                "status", "Authenticated via Spring Security Filter Chain"));
    }

}