package com.vietcao.E_Commerce.Project.services;

import org.springframework.stereotype.Service;

import com.vietcao.E_Commerce.Project.entities.Product;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.vietcao.E_Commerce.Project.repositories.ProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
