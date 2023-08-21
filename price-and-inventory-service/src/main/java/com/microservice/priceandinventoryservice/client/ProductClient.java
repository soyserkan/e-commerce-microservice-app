package com.microservice.priceandinventoryservice.client;

import com.microservice.priceandinventoryservice.dto.InternalApiResponse;
import com.microservice.priceandinventoryservice.dto.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProductClient {

    @GetExchange("get/barcode/{barcode}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("barcode") String barcode);

}
