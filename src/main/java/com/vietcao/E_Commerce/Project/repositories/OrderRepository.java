package com.vietcao.E_Commerce.Project.repositories;

import com.vietcao.E_Commerce.Project.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
