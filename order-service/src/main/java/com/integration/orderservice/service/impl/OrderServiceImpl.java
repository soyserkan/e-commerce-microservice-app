package com.integration.orderservice.service.impl;

import com.integration.orderservice.client.ProductClient;
import com.integration.orderservice.dto.ProductResponse;
import com.integration.orderservice.enums.Language;
import com.integration.orderservice.repository.OrderRepository;
import com.integration.orderservice.repository.entity.Order;
import com.integration.orderservice.repository.entity.Product;
import com.integration.orderservice.dto.OrderCreateRequest;
import com.integration.orderservice.dto.OrderUpdateRequest;
import com.integration.orderservice.dto.InternalApiResponse;
import com.integration.orderservice.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final Language language = Language.EN;

    @Autowired
    private final ProductClient productClient;

    @Override
    public Order createOrder(OrderCreateRequest productCreateRequest) {
        return null;
    }

    @Override
    public Order getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getOrders() {
        log.debug("[{}][getOrders] -> request: {}", this.getClass().getSimpleName());
        List<Order> orders = orderRepository.getAllByDeletedFalse();
        if (orders.isEmpty()) {
            //throw new ProductNotFoundException(language, MessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "Products not found");
        }
        ProductResponse productResponse =  productClient.getProduct(1).getPayload();
        log.debug(String.valueOf(productResponse));


        // orders.forEach(order ->
        //      order.setOrderProducts(
        //            employeeClient.findByDepartment(department.getId()))
        // );
        log.debug("[{}][getOrders] -> request: {}", this.getClass().getSimpleName(), orders);
        return orders;
    }

    @Override
    public Order updateOrder(Long orderId, OrderUpdateRequest productUpdateRequest) {
        return null;
    }

    @Override
    public Order deleteOrder(Long orderId) {
        return null;
    }
}


