package com.integration.orderservice.service;

import com.integration.orderservice.repository.entity.Order;
import com.integration.orderservice.dto.OrderCreateRequest;
import com.integration.orderservice.dto.OrderUpdateRequest;

import java.util.List;

public interface IOrderService {
    Order createOrder(OrderCreateRequest productCreateRequest);

    Order getOrder(Long orderId);

    List<Order> getOrders();

    Order updateOrder(Long orderId, OrderUpdateRequest productUpdateRequest);

    Order deleteOrder(Long orderId);
}
