package com.example.challenge.service;

import com.example.challenge.model.Order;
import com.example.challenge.model.OrderItem;
import com.example.challenge.model.Product;
import com.example.challenge.repository.OrderItemRepository;
import com.example.challenge.repository.OrderRepository;
import com.example.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public Page<Order> findAll(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(UUID uuid) {
        return orderRepository.findById(uuid);
    }

    public void deleteOrder(UUID uuid) {
        orderRepository.delete(new Order(uuid));
    }

    public void deleteOrderItem(UUID uuid) {
        orderItemRepository.deleteByOrder(new Order(uuid));
    }

    public OrderItem findByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }
}
