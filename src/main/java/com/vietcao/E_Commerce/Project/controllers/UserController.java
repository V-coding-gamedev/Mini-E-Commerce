/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vietcao.E_Commerce.Project.controllers;

import com.vietcao.E_Commerce.Project.entities.User;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vietcao.E_Commerce.Project.services.UserService;

// Controller chỉ gọi service, k làm gì thêm

@RestController 
public class UserController {
    private final UserService userService; 

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAll(); 
    }
    
    @RequestMapping("/")
    public String index(){
        return "index.html"; 
    }
}
