package com.example.challenge.repository;

import com.example.challenge.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    Page<Product> findAll(Pageable pageable);
}
