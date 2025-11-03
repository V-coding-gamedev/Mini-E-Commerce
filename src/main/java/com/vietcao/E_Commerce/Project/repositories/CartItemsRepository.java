package com.vietcao.E_Commerce.Project.repositories;

import com.vietcao.E_Commerce.Project.entities.*;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
    Optional<CartItems> findByCartIdAndProductId(int cartId, int productId);
    
    Optional<CartItems> findByCartAndProduct(Cart cart, Product product); 
}
