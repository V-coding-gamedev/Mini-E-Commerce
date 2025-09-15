package com.vietcao.E_Commerce.Project.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

//là một annotation trong Java (cụ thể là JPA – Java Persistence API). Nó dùng để đánh dấu một class là một thực thể (entity), tức là class đó sẽ ánh xạ (map) tới một bảng trong cơ sở dữ liệu.
@Entity 
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id; 
    
    String name;
    String description;
    Float price; 
    Long stock;
    String image_url; 
    LocalDateTime created_at; 
    
    // Khai báo quan hệ Many-to-One với Category
    @ManyToOne
    @JoinColumn(name = "category_id") // cột foreign key trong bảng Product
    Category category_id; 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    
    
    
}
