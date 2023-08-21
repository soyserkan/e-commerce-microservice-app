package com.integration.orderservice.repository.entity;

public enum OrderStatus {
    Created,
    Picking,
    Invoiced,
    Shipped,
    Cancelled,
    Delivered,
    Returned,
    UnPacked,
    UnSupplied,
}
