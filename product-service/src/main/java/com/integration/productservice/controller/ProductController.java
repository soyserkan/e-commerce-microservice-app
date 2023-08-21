package com.integration.productservice.controller;

import com.integration.productservice.enums.Language;
import com.integration.productservice.exception.enums.MessageCodes;
import com.integration.productservice.exception.utils.MessageUtils;
import com.integration.productservice.repository.entity.Product;
import com.integration.productservice.dto.ProductCreateRequest;
import com.integration.productservice.dto.ProductUpdateRequest;
import com.integration.productservice.dto.InternalApiResponse;
import com.integration.productservice.dto.Message;
import com.integration.productservice.dto.ProductResponse;
import com.integration.productservice.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/product")
@RequiredArgsConstructor
class ProductController {
    private final IProductService productService;
    private final Language language = Language.EN;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public InternalApiResponse<ProductResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        log.debug("[{}][createProduct] -> request:{}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = productService.createProduct(productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> response:{}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .message(Message.builder()
                        .title(MessageUtils.getMessage(language, MessageCodes.SUCCESS))
                        .description(MessageUtils.getMessage(language, MessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("productId") Long productId) {
        log.debug("[{}][getProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        Product product = productService.getProduct(productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][getProduct] -> response:{}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get/barcode/{barcode}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("barcode") String barcode) {
        log.debug("[{}][getProduct] -> request barcode: {}", this.getClass().getSimpleName(), barcode);
        Product product = productService.getProduct(barcode);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][getProduct] -> response:{}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update/{productId}")
    public InternalApiResponse<ProductResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductUpdateRequest productUpdateRequest) {
        log.debug("[{}][updateProduct] -> request: {} {}", this.getClass().getSimpleName(), productId, productUpdateRequest);
        Product product = productService.updateProduct(productId, productUpdateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .message(Message.builder()
                        .title(MessageUtils.getMessage(language, MessageCodes.SUCCESS))
                        .description(MessageUtils.getMessage(language, MessageCodes.PRODUCT_SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "get")
    public InternalApiResponse<List<ProductResponse>> getProducts() {
        log.debug("[{}][getProducts]", this.getClass().getSimpleName());
        List<Product> products = productService.getProducts();
        List<ProductResponse> productResponses = convertProductResponseList(products);
        log.debug("[{}][getProducts] -> response: {}", this.getClass().getSimpleName(), productResponses);
        return InternalApiResponse.<List<ProductResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();

    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/delete/{productId}")
    public InternalApiResponse<ProductResponse> deleteProduct(@PathVariable("productId") Long productId) {
        log.debug("[{}][deleteProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        Product product = productService.deleteProduct(productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][delete] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .message(Message.builder()
                        .title(MessageUtils.getMessage(language, MessageCodes.SUCCESS))
                        .description(MessageUtils.getMessage(language, MessageCodes.PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    private List<ProductResponse> convertProductResponseList(List<Product> productList) {
        return productList.stream()
                .map(arg -> ProductResponse.builder()
                        .productId(arg.getId())
                        .userId(arg.getUserId())
                        .categoryId(arg.getCategoryId())
                        .brandId(arg.getBrandId())
                        .barcode(arg.getBarcode())
                        .title(arg.getTitle())
                        .status(arg.isStatus())
                        .listPrice(arg.getListPrice())
                        .salePrice(arg.getSalePrice())
                        .vatRate(arg.getVatRate())
                        .currency(arg.getCurrency())
                        .quantity(arg.getQuantity())
                        .description(arg.getDescription())
                        .shortDescription(arg.getShortDescription())
                        .images(arg.getImages())
                        .mainProductCode(arg.getMainProductCode())
                        .modelCode(arg.getModelCode())
                        .platformCode(arg.getPlatformCode())
                        .variantProperties(arg.getVariantProperties())
                        .productCreatedDate(arg.getCreatedAt().getTime())
                        .productUpdatedDate(arg.getUpdatedAt().getTime())
                        .build())
                .collect(Collectors.toList());
    }

    private static ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .userId(product.getUserId())
                .categoryId(product.getCategoryId())
                .brandId(product.getBrandId())
                .barcode(product.getBarcode())
                .title(product.getTitle())
                .status(product.isStatus())
                .listPrice(product.getListPrice())
                .salePrice(product.getSalePrice())
                .vatRate(product.getVatRate())
                .currency(product.getCurrency())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .shortDescription(product.getShortDescription())
                .images(product.getImages())
                .mainProductCode(product.getMainProductCode())
                .modelCode(product.getModelCode())
                .platformCode(product.getPlatformCode())
                .variantProperties(product.getVariantProperties())
                .productCreatedDate(product.getCreatedAt().getTime())
                .productUpdatedDate(product.getUpdatedAt().getTime())
                .build();
    }

}

