package com.integration.productservice.service.impl;

import com.integration.productservice.enums.Language;
import com.integration.productservice.exception.enums.MessageCodes;
import com.integration.productservice.exception.exceptions.ProductNotCreatedException;
import com.integration.productservice.exception.exceptions.ProductNotFoundException;
import com.integration.productservice.exception.exceptions.ProductNotUpdatedException;
import com.integration.productservice.repository.ProductRepository;
import com.integration.productservice.repository.entity.Product;
import com.integration.productservice.dto.ProductCreateRequest;
import com.integration.productservice.dto.ProductUpdateRequest;
import com.integration.productservice.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final Language language = Language.EN;

    @Override
    public Product createProduct(ProductCreateRequest productCreateRequest) {
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        try {
            Product product = Product.builder()
                    .userId(productCreateRequest.getUserId())
                    .categoryId(productCreateRequest.getCategoryId())
                    .brandId(productCreateRequest.getBrandId())
                    .barcode(productCreateRequest.getBarcode())
                    .title(productCreateRequest.getTitle())
                    .status(productCreateRequest.getStatus())
                    .listPrice(productCreateRequest.getListPrice())
                    .salePrice(productCreateRequest.getSalePrice())
                    .vatRate(productCreateRequest.getVatRate())
                    .currency(productCreateRequest.getCurrency())
                    .quantity(productCreateRequest.getQuantity())
                    .description(productCreateRequest.getDescription())
                    .shortDescription(productCreateRequest.getShortDescription())
                    .images(productCreateRequest.getImages())
                    .mainProductCode(productCreateRequest.getMainProductCode())
                    .modelCode(productCreateRequest.getModelCode())
                    .platformCode(productCreateRequest.getPlatformCode())
                    .variantProperties(productCreateRequest.getVariantProperties())
                    .build();

            Product productResponse = productRepository.save(product);
            log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (Exception exception) {
            throw new ProductNotCreatedException(
                    language,
                    MessageCodes.PRODUCT_NOT_CREATED_EXCEPTION,
                    "product request: " + exception.getMessage()
            );
        }
    }

    @Override
    public Product getProduct(Long productId) {
        log.debug("[{}][getProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepository.getByIdAndDeletedFalse(productId);
        if (Objects.isNull(product)) {
            throw new ProductNotFoundException(
                    language,
                    MessageCodes.PRODUCT_NOT_FOUND_EXCEPTION,
                    "Product not found for product id: " + productId
            );
        }
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), product);
        return product;
    }

    @Override
    public Product getProduct(String barcode) {
        log.debug("[{}][getProduct] -> request barcode: {}", this.getClass().getSimpleName(), barcode);
        Product product = productRepository.getByBarcodeAndDeletedFalse(barcode);
        if (Objects.isNull(product)) {
            throw new ProductNotFoundException(
                    language,
                    MessageCodes.PRODUCT_NOT_FOUND_EXCEPTION,
                    "Product not found for product barcode: " + barcode
            );
        }
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), product);
        return product;
    }

    @Override
    public List<Product> getProducts() {
        log.debug("[{}][getProducts] -> request: {}", this.getClass().getSimpleName());
        List<Product> products = productRepository.getAllByDeletedFalse();
        if (products.isEmpty()) {
            throw new ProductNotFoundException(language, MessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "Products not found");
        }
        log.debug("[{}][getProducts] -> request: {}", this.getClass().getSimpleName(), products);
        return products;
    }

    @Override
    public Product updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {
        log.debug("[{}][updateProduct] -> request: {}", this.getClass().getSimpleName(), productUpdateRequest);
        try {
            Product product = this.getProduct(productId);
            product.setUserId(productUpdateRequest.getUserId());
            product.setCategoryId(productUpdateRequest.getCategoryId());
            product.setBrandId(productUpdateRequest.getBrandId());
            product.setBarcode(productUpdateRequest.getBarcode());
            product.setTitle(productUpdateRequest.getTitle());
            product.setStatus(productUpdateRequest.getStatus());
            product.setListPrice(productUpdateRequest.getListPrice());
            product.setSalePrice(productUpdateRequest.getSalePrice());
            product.setVatRate(productUpdateRequest.getVatRate());
            product.setCurrency(productUpdateRequest.getCurrency());
            product.setQuantity(productUpdateRequest.getQuantity());
            product.setDescription(productUpdateRequest.getDescription());
            product.setShortDescription(productUpdateRequest.getShortDescription());
            product.setImages(productUpdateRequest.getImages());
            product.setMainProductCode(productUpdateRequest.getMainProductCode());
            product.setModelCode(productUpdateRequest.getModelCode());
            product.setPlatformCode(productUpdateRequest.getPlatformCode());
            product.setVariantProperties(productUpdateRequest.getVariantProperties());
            product.setUpdatedAt(new Date());

            Product productResponse = productRepository.save(product);

            log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (Exception exception) {
            throw new ProductNotUpdatedException(
                    language,
                    MessageCodes.PRODUCT_NOT_UPDATED_EXCEPTION,
                    "product request: " + productUpdateRequest.toString()
            );
        }
    }

    @Override
    public Product deleteProduct(Long productId) {
        log.debug("[{}][deleteProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        Product product;
        try {
            product = getProduct(productId);
            product.setDeleted(true);
            product.setUpdatedAt(new Date());
            Product productResponse = productRepository.save(product);
            log.debug("[{}][deleteProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (ProductNotFoundException productNotFoundException) {
            throw new ProductNotFoundException(language, MessageCodes.PRODUCT_ALREADY_DELETED, "Product already deleted product id: " + productId);
        }
    }
}

