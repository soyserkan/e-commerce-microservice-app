package com.microservice.priceandinventoryservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("PriceAndInventory")
public class PriceAndInventory implements Serializable {
    @Id
    private String barcode;
    private Long userId;
    private long quantity;
    private double listPrice;
    private double salePrice;
}
