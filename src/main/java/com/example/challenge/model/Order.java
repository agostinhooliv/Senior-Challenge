package com.example.challenge.model;

import com.example.challenge.validator.Discount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idOder")
    private UUID idOder;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;
    @Column(name = "discount")
    @NotNull(message = "Required field!")
    @Discount
    private float discount;
    @Column(name = "balance")
    private double balance;
    @Column(name = "status")
    private char status;

    public Order() {
    }

    public Order(String description, Date date, float discount, double balance, char status) {
        this.description = description;
        this.date = date;
        this.discount = discount;
        this.balance = balance;
        this.status = status;
    }

    public UUID getIdOder() {
        return idOder;
    }

    public void setIdOder(UUID idOder) {
        this.idOder = idOder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
