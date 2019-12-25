package com.example.warehouse.model;

import java.util.UUID;

public class Order {

    public UUID getId() {
        return id;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public double getTotalCost() {
        return Double.parseDouble(totalCost);
    }

    private UUID id;
    private Integer totalAmount;
    private OrderStatus orderStatus;
    public String totalCost;

    public Order() {}

    public Order(UUID id,
                 Integer totalAmount,
                 Integer orderStatus,
                 String totalCost) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.totalCost = totalCost;
        this.orderStatus = OrderStatus.values()[orderStatus];
    }
}

