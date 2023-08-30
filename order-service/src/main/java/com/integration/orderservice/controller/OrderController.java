package com.integration.orderservice.controller;

import com.integration.orderservice.dto.Message;
import com.integration.orderservice.enums.Language;
import com.integration.orderservice.exception.enums.MessageCodes;
import com.integration.orderservice.exception.utils.MessageUtils;
import com.integration.orderservice.repository.entity.Order;
import com.integration.orderservice.dto.InternalApiResponse;
import com.integration.orderservice.dto.OrderResponse;
import com.integration.orderservice.service.IOrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<InternalApiResponse<List<OrderResponse>>> getOrders() {
        log.debug("[{}][getOrders]", this.getClass().getSimpleName());
        List<Order> orders = orderService.getOrders();
        List<OrderResponse> orderResponses = convertProductResponseList(orders);
        log.debug("[{}][getOrders] -> response: {}", this.getClass().getSimpleName(), orderResponses);
        return CompletableFuture.supplyAsync(() -> InternalApiResponse.<List<OrderResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(orderResponses)
                .build());
    }

    public CompletableFuture<InternalApiResponse<String>> fallbackMethod(RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> InternalApiResponse.<String>builder()
                .message(Message.builder()
                        .title(MessageUtils.getMessage(Language.EN, MessageCodes.ERROR))
                        .description("something went wrong")
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(runtimeException.getMessage()))
                .build());
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

