/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vietcao.E_Commerce.Project.controllers;

import com.vietcao.E_Commerce.Project.entities.User;
import com.vietcao.E_Commerce.Project.repositories.UserRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vietcao.E_Commerce.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controller chỉ gọi service, k làm gì thêm

@RestController 
public class UserController {
    private final UserService userService; 

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/")
    public String index(){
        return "index.html"; 
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAll(); 
    }
    
//    @PostMapping("/register")
//    public User createNewUser (@RequestParam String username, @RequestParam String password, @RequestParam String email) {
//        return userService.register(username, password, email); 
//    }
    
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser (@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        try {
            User user = userService.register(username, password, email);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
//            System.out.println("Message: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
