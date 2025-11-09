package com.vietcao.E_Commerce.Project.controllers;

import com.vietcao.E_Commerce.Project.dtos.OrderItemsResponse;
import com.vietcao.E_Commerce.Project.entities.Order;
import com.vietcao.E_Commerce.Project.services.CartService;
import com.vietcao.E_Commerce.Project.services.OrderService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final CartService cartService;
    
    private final OrderService orderService; 

    // List<OrderItemsResponse> orderItems; 
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService; 
    }
    
    @GetMapping("/getOrder")
    private List<Order> getOrder(@RequestParam Long userId){
        return orderService.getOrder(userId); 
    }
    
    
    // @PathVariable lấy giá trị động từ URL (vd: /api/cart/5 → userId = 5)
    @PostMapping("/confirmOrder/{userId}")
    private double confirmOrderAndGetTotalPrice(@PathVariable Long userId){
        List<OrderItemsResponse> orderItems = cartService.getCartItemsByUserId(userId)
                .stream()
                .map(item -> new OrderItemsResponse(
                item.getCart().getId(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getProduct().getPrice()
        )).toList();
        
        double totalPrice = computeTotalPrice(orderItems); 
        
        createOrUpdateOrder(userId, totalPrice); 

        return totalPrice;
    }
    
    private void createOrUpdateOrder(Long userId, double totalPrice){
        orderService.createOrUpdatePendingOrder(userId, totalPrice);
    }

    private double computeTotalPrice(List<OrderItemsResponse> orderItems) {
        return orderService.computeTotalPrice(orderItems); 
    }
}
