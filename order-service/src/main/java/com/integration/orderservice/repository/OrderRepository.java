package com.integration.orderservice.repository;

import com.integration.orderservice.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getByIdAndDeletedFalse(Long orderId);
    List<Order> getAllByDeletedFalse();
}