package com.vietcao.E_Commerce.Project.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    
    // Khai báo quan hệ Many-to-One với Category
    @OneToOne
    @JoinColumn(name = "user_id") // cột foreign key trong bảng Product
    User user_id; 
    
    LocalDateTime created_at; 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    
    
    
}
