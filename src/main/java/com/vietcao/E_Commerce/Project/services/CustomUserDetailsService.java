package com.vietcao.E_Commerce.Project.services;

import com.vietcao.E_Commerce.Project.entities.User;
import com.vietcao.E_Commerce.Project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Entity repository sẵn có
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // System.out.println("Đang load user từ DB: " + user.getUsername() + " | password: " + user.getPassword() + " | role: " + user.getRole());

        
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()) // hoặc convert danh sách role
                .build();
    }
    
}
