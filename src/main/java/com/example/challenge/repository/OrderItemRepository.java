package com.example.challenge.repository;

import com.example.challenge.model.Order;
import com.example.challenge.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderItemRepository extends CrudRepository<OrderItem, UUID> {
    OrderItem findByOrder(Order order);
    void deleteByOrder(Order order);
}
