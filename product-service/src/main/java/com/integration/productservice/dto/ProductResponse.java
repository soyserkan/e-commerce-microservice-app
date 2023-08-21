package com.integration.productservice.dto;

import com.integration.productservice.repository.entity.VariantProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
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
