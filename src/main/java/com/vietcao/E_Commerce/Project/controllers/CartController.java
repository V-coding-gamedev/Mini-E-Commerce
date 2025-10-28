package com.vietcao.E_Commerce.Project.controllers;

import com.vietcao.E_Commerce.Project.dtos.CartItemsResponse;
import com.vietcao.E_Commerce.Project.entities.Cart;
import com.vietcao.E_Commerce.Project.entities.CartItems;
import com.vietcao.E_Commerce.Project.entities.User;
import com.vietcao.E_Commerce.Project.services.CartService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam long quantity) {
        try {
            Cart cart = cartService.getOrCreateActiveCart(userId);
            cartService.addProductToCart(cart, productId, quantity);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/api/cart/{userId}")
    public List<CartItemsResponse> getCart(@PathVariable Long userId) {
        return cartService.getCartItemsByUserId(userId)
                .stream()
                .map(item -> new CartItemsResponse(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getProduct().getImage_url(),
                item.getQuantity()
        )).toList();
    }
}
