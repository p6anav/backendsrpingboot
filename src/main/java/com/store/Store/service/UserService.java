package com.store.Store.service;

import com.store.Store.dto.UserDTO;
import com.store.Store.entity.User;
import com.store.Store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User
    public boolean registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            return false; // User already exists
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword()); // Store password as plain text

        userRepository.save(newUser);
        return true;
    }

    // Authenticate User (Login)
    public boolean authenticateUser(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        return user != null && userDTO.getPassword().equals(user.getPassword()); // Direct string comparison
    }
}