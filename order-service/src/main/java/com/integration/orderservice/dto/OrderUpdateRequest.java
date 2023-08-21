package com.integration.orderservice.dto;

import com.integration.orderservice.repository.entity.Discounts;
import com.integration.orderservice.repository.entity.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderUpdateRequest {
    private Long userId;
    private Long platformId;
    private String orderNumber;
    private Enum<OrderStatus> status;
    private Date orderDate = new Date();
    private Long total_quantity;
    private Double grossAmount;
    private Double totalPrice;
    private Double totalDiscounts;
    private List<Discounts> discounts;
    private Long taxRate;
    private String customerFullName;
    private String customerEmail;
    private Integer identityNumber;
    private String note;
    private String cargoProviderName;
    private String cargoNumber;
    private String trackingNumber;
    private String trackingLink;
}

