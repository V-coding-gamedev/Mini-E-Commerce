package com.vietcao.E_Commerce.Project.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CartItems")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    
    @ManyToOne
    @JoinColumn(name = "cart_id")      
    Cart cart;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    
    long quantity; 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
