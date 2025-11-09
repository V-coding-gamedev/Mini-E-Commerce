package com.vietcao.E_Commerce.Project.repositories;

import com.vietcao.E_Commerce.Project.entities.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser_Id(Long userId); 
    
    Optional<Order> findByUserIdAndStatus(Long userId, String status);
}
