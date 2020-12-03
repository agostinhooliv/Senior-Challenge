package com.example.challenge.model;

import com.example.challenge.validator.ProductStatus;
import com.example.challenge.validator.ProductType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idProduct")
    private UUID idProduct;
    @Column(unique = true)
    @NotBlank(message = "Field is mandatory!")
    private String name;
    @NotNull(message = "Field is mandatory!")
    @Column(name = "price")
    private double price;
    @Column(name = "type")
    @ProductType
    private char type;
    @Column(name = "status")
    @ProductStatus
    private char status;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, double price, char type, char status) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.status = status;
    }

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
