package com.example.challenge.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idOrderItem")
    private UUID id;
    @OneToOne
    @JoinColumn(name = "idOrder")
    private Order order;
    @ManyToMany
    private List<Product> products = new ArrayList<>();

    public OrderItem() {
    }

    public OrderItem(Order order, List<Product> products) {
        this.order = order;
        this.products = products;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
