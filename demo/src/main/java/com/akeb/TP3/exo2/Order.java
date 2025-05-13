package com.akeb.TP3.exo2;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final double totalAmount;
    private final LocalDateTime orderDate;
    private final String customerName;

    public Order(String customerName, double totalAmount) {
        this.orderId = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.orderDate = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }
}
