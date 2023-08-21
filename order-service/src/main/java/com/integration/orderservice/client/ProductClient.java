package com.integration.orderservice.client;

import com.integration.orderservice.dto.ProductResponse;
import com.integration.orderservice.dto.InternalApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProductClient {

    @GetExchange("get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("productId") Integer productId);

}
