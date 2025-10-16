package com.vietcao.E_Commerce.Project.repositories;

import com.vietcao.E_Commerce.Project.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
    
}
