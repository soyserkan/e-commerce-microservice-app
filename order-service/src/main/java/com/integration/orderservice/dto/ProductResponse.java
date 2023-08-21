package com.integration.orderservice.dto;

import com.integration.orderservice.repository.entity.VariantProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ProductResponse {
    private Long productId;
    private Long userId;
    private Long categoryId;
    private Long brandId;
    private String barcode;
    private String title;
    private Boolean status;
    private Double listPrice;
    private Double salePrice;
    private Double vatRate;
    private String currency;
    private Long quantity;
    private String description;
    private String shortDescription;
    private List<String> images;
    private String mainProductCode;
    private String modelCode;
    private String platformCode;
    private List<VariantProperties> variantProperties;
    private Long productCreatedDate;
    private Long productUpdatedDate;
}
