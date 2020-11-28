package com.example.challenge.controller;

import com.example.challenge.model.Product;
import com.example.challenge.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @ResponseBody
    @GetMapping("/products")
    public Iterable<Product> greeting() {

        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody Product product) {
        Product product1 = service.add(product);

        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }
}
