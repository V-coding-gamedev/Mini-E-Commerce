package com.vietcao.E_Commerce.Project.controllers;

import com.vietcao.E_Commerce.Project.dtos.OrderItemsResponse;
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

    // @PathVariable lấy giá trị động từ URL (vd: /api/cart/5 → userId = 5)
//    @GetMapping("/confirmOrder/{userId}")
//    public List<OrderItemsResponse> confirmOrder(@PathVariable Long userId) {
//        List<OrderItemsResponse> orderItems = cartService.getCartItemsByUserId(userId)
//                .stream()
//                .map(item -> new OrderItemsResponse(
//                item.getCart().getId(),
//                item.getProduct().getId(),
//                item.getProduct().getName(),
//                item.getQuantity(),
//                item.getProduct().getPrice()
//        )).toList();
//        
//        double totalPrice = calculateTotalPrice(orderItems); 
//
//        return orderItems;
//    }
//
    
    // @PathVariable lấy giá trị động từ URL (vd: /api/cart/5 → userId = 5)
    @GetMapping("/confirmOrder/{userId}")
    private double getTotalOrderPrice(@PathVariable Long userId){
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
        
        addOrder(userId, totalPrice); 

        return totalPrice;
    }
    
    private void addOrder(Long userId, double totalPrice){
        orderService.createOrder(userId, totalPrice);
    }

    private double computeTotalPrice(List<OrderItemsResponse> orderItems) {
        double totalPrice = 0;
        double subPrice = 0;

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItemsResponse item = orderItems.get(i);
            subPrice = item.quantity() * item.price();
            totalPrice += subPrice;
        }

        return totalPrice;
    }
}
