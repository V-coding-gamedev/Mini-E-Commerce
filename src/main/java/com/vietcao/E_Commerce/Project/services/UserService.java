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

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User register(String username, String password, String email) {
        boolean isUserExist = userRepository.existsByEmail(email) || userRepository.existsByUsername(username); 
                
        if (isUserExist){
            throw new RuntimeException("Email or username has already used"); 
        }

        User user = new User(username, passwordEncoder.encode(password), email, "USER");

        return userRepository.save(user);
    }
}
