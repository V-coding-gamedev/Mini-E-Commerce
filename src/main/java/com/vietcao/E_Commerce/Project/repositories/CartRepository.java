package com.vietcao.E_Commerce.Project.repositories;

import com.vietcao.E_Commerce.Project.entities.Cart;
import com.vietcao.E_Commerce.Project.entities.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>{
    Optional<Cart> findByUserIdAndStatus(Long userId, String status);
    
    // Optional<Cart> CreateNewCart(Long userId, String status);

}
