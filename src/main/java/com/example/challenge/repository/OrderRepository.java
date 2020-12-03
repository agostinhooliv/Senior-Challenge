package com.example.challenge.repository;

import com.example.challenge.model.Order;
import com.example.challenge.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
    Page<Order> findAll(Pageable pageable);
}
