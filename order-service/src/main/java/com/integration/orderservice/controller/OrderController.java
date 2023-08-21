package com.integration.orderservice.controller;

import com.integration.orderservice.enums.Language;
import com.integration.orderservice.repository.entity.Order;
import com.integration.orderservice.dto.InternalApiResponse;
import com.integration.orderservice.dto.OrderResponse;
import com.integration.orderservice.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/order")
@RequiredArgsConstructor
class OrderController {
    private final IOrderService orderService;
    private final Language language = Language.EN;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "get")
    public InternalApiResponse<List<OrderResponse>> getOrders() {
        log.debug("[{}][getOrders]", this.getClass().getSimpleName());
        List<Order> products = orderService.getOrders();
        List<OrderResponse> productResponses = convertProductResponseList(products);
        log.debug("[{}][getOrders] -> response: {}", this.getClass().getSimpleName(), productResponses);
        return InternalApiResponse.<List<OrderResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();

    }

    private List<OrderResponse> convertProductResponseList(List<Order> productList) {
        return productList.stream()
                .map(arg -> OrderResponse.builder()
                        .orderId(arg.getId())
                        .userId(arg.getUserId())
                        .productCreatedDate(arg.getCreatedAt().getTime())
                        .productUpdatedDate(arg.getUpdatedAt().getTime())
                        .build())
                .collect(Collectors.toList());
    }


}

