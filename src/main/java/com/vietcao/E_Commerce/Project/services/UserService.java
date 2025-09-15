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

@Service
public class UserService {
    UserRepository userRepository; 

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> getAll(){
        return userRepository.findAll(); 
    }
}
