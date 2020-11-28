package com.example.challenge.service;

import com.example.challenge.model.Product;
import com.example.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    ProductRepository productRepository;

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public Product add(Product product){
        return productRepository.save(product);
    }
}
