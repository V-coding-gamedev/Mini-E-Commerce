/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vietcao.E_Commerce.Project.services;

import com.vietcao.E_Commerce.Project.entities.User;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.vietcao.E_Commerce.Project.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /* 
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User register(String username, String password, String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User(username, passwordEncoder.encode(password), email, "USER");

        return userRepository.save(user);
    }
}
