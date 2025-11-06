package com.vietcao.E_Commerce.Project.services;

import com.vietcao.E_Commerce.Project.entities.*;
import com.vietcao.E_Commerce.Project.repositories.OrderRepository;
import com.vietcao.E_Commerce.Project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    OrderRepository orderRepository; 
    
    public void createOrder(Long userId, double totalPrice){
        User user = userRepository.findById(userId).get(); 
        
        Order order = new Order(); 
        order.setUser_id(user);
        order.setTotalPrice((float) totalPrice);
        orderRepository.save(order); 
    }
}
