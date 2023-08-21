package com.integration.productservice.service;

import com.integration.productservice.repository.entity.Product;
import com.integration.productservice.dto.ProductCreateRequest;
import com.integration.productservice.dto.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductCreateRequest productCreateRequest);

    Product getProduct(Long productId);

    Product getProduct(String barcode);

    List<Product> getProducts();

    Product updateProduct(Long productId, ProductUpdateRequest productUpdateRequest);

    Product deleteProduct(Long productId);
}
