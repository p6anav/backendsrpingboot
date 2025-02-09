package com.store.Store.controller;

import com.store.Store.dto.UserDTO;
import com.store.Store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allow cross-origin requests (needed for frontend)
public class AuthController {

    @Autowired
    private UserService userService;

    // Signup API
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        boolean isRegistered = userService.registerUser(userDTO);
        if (isRegistered) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {
        boolean isAuthenticated = userService.authenticateUser(userDTO);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/health")
public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("Server is up");
}

}