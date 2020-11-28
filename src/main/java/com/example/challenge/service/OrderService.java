package com.example.challenge.service;

import com.example.challenge.model.Order;
import com.example.challenge.model.OrderItem;
import com.example.challenge.repository.OrderItemRepository;
import com.example.challenge.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//
//    public Page<Order> findAll(Pageable pageable) {
//        return orderRepository.findAll(pageable);
//    }
//
//    public void saveOrder(Order order) {
//        orderRepository.save(order);
//    }
//
//    public void saveOrderItem(OrderItem orderItem) {
//        orderItemRepository.save(orderItem);
//    }
//
//    public void deleteOrder(Order order) {
//        orderRepository.delete(order);
//    }
//
//    public void deleteOrderItem(OrderItem orderItem) {
//        orderItemRepository.delete(orderItem);
//    }
//
//    public Optional<Order> findById(UUID idOrder){
//        return orderRepository.findById(idOrder);
//    }
//
//    public OrderItem findByOrder(Order order){
//        return orderItemRepository.findByOrder(order);
//    }
}
