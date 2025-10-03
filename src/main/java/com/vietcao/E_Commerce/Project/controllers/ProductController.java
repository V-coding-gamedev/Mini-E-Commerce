package com.vietcao.E_Commerce.Project.controllers;

import com.vietcao.E_Commerce.Project.entities.Product;
import com.vietcao.E_Commerce.Project.services.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class ProductController {
    private final ProductService productService; 

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAll(); 
    }
}
