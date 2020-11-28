package com.example.challenge.service;

import com.example.challenge.model.Product;
import com.example.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    public Optional<Product> findById(UUID idProduct){
        return productRepository.findById(idProduct);
    }

    public void deleteById(UUID idProduct){
        productRepository.deleteById(idProduct);
    }
}
