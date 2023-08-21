package com.integration.orderservice.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "platform_id")
    private long platformId;

    @Column(name = "orderNumber")
    private String orderNumber;

    @Column
    private Enum<OrderStatus> status;

    @Column(name = "order_date")
    private Date orderDate = new Date();

    @Column
    private long total_quantity;

    @Column(name = "gross_amount", columnDefinition = "numeric(8,2)")
    private double grossAmount;

    @Column(name = "total_price", columnDefinition = "numeric(8,2)")
    private double totalPrice;

    @Column(name = "total_discounts", columnDefinition = "numeric(8,2)")
    private double totalDiscounts;

    @Column(columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Discounts> discounts;

    @Column(name = "tax_rate")
    private long taxRate;

    @Column(name = "customer_full_name")
    private String customerFullName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "identity_number")
    private int identityNumber;

    @Column
    private String note;

    @Column(name = "cargo_provider_name")
    private String cargoProviderName;

    @Column(name = "cargo_number")
    private String cargoNumber;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "tracking_link")
    private String trackingLink;

    @Builder.Default
    @Column(name = "updated_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    @Builder.Default
    @Column(name = "created_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private boolean deleted = false;
}

