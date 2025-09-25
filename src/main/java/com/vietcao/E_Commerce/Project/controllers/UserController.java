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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controller chỉ gọi service, k làm gì thêm

@RestController 
public class UserController {
    private final UserService userService; 
    
    @Autowired
    public UserRepository userRepository; 
    
//    public User user; 

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
//    public ResponseEntity<?> authenticateUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
//        
//    }
}
