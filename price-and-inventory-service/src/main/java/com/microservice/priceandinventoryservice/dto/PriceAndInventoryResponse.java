package com.microservice.priceandinventoryservice.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PriceAndInventoryResponse {
    private String barcode;
    private Long userId;
    private Double listPrice;
    private Double salePrice;
    private Long quantity;
}
