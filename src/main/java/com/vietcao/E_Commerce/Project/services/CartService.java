package com.vietcao.E_Commerce.Project.services;

import com.vietcao.E_Commerce.Project.entities.Cart;
import com.vietcao.E_Commerce.Project.entities.CartItems;
import com.vietcao.E_Commerce.Project.entities.Product;
import com.vietcao.E_Commerce.Project.entities.User;
import com.vietcao.E_Commerce.Project.repositories.CartRepository;
import com.vietcao.E_Commerce.Project.repositories.ProductRepository;
import com.vietcao.E_Commerce.Project.repositories.UserRepository;
import com.vietcao.E_Commerce.Project.repositories.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    CartItemsRepository cartItemsRepository; 
    
    public Cart getOrCreateActiveCart(Long userId) {
        User user = userRepository.findById(userId).get();

        return cartRepository.findByUserIdAndStatus(userId, "ACTIVE")
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setStatus("ACTIVE");
                    return cartRepository.save(newCart);
                });
    }

    public void addProductToCart(Cart cart, Long productId, long quantity) {
        Product product = productRepository.findById(productId).get();

        CartItems item = new CartItems();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);
        cartItemsRepository.save(item);
    }
    
    public List<CartItems> getCartItemsByUserId(Long userId) {
        Cart cart = cartRepository.findByUserIdAndStatus(userId, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
        return cart.getCartItems();
    }
}
