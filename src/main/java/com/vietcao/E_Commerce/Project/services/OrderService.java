package com.vietcao.E_Commerce.Project.services;

import com.vietcao.E_Commerce.Project.dtos.OrderItemsResponse;
import com.vietcao.E_Commerce.Project.entities.*;
import com.vietcao.E_Commerce.Project.repositories.OrderRepository;
import com.vietcao.E_Commerce.Project.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    OrderRepository orderRepository; 
    
    public List<Order> getOrder(Long userId){
        return orderRepository.findByUser_Id(userId); 
    }
    
    public void createOrUpdatePendingOrder(Long userId, double totalPrice) {
        Optional<Order> existingOrder = orderRepository.findByUserIdAndStatus(userId, "pending");
        
        User user = userRepository.findById(userId).get(); 

        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setTotalPrice((float) totalPrice);
            orderRepository.save(order);
        } else {
            Order newOrder = new Order();
            newOrder.setUser(user);
            newOrder.setTotalPrice((float) totalPrice);
            newOrder.setStatus("pending");
            orderRepository.save(newOrder);
        }
    }
    
    public double computeTotalPrice(List<OrderItemsResponse> orderItems) {
        double totalPrice = 0;
        double subPrice = 0;

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItemsResponse item = orderItems.get(i);
            subPrice = item.quantity() * item.price();
            totalPrice += subPrice;
        }
        
        totalPrice = Math.ceil(totalPrice * 100.0) / 100.0;

        return totalPrice;
    }
}
