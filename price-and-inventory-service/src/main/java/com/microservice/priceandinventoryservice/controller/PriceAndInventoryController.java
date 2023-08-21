package com.microservice.priceandinventoryservice.controller;

import com.microservice.priceandinventoryservice.dto.InternalApiResponse;
import com.microservice.priceandinventoryservice.dto.PriceAndInventoryResponse;
import com.microservice.priceandinventoryservice.repository.entity.PriceAndInventory;
import com.microservice.priceandinventoryservice.service.IPriceAndInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/price-and-inventory")
@RequiredArgsConstructor
public class PriceAndInventoryController {

    private final IPriceAndInventoryService priceAndInventoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get/{barcode}")
    public InternalApiResponse<PriceAndInventoryResponse> getPriceAndInventory(@PathVariable("barcode") String barcode) {
        log.debug("[{}][getPriceAndInventory] -> request barcode: {}", this.getClass().getSimpleName(), barcode);
        PriceAndInventory priceAndInventory = priceAndInventoryService.getPriceAndInventory(barcode);
        PriceAndInventoryResponse priceAndInventoryResponse = convertProductResponse(priceAndInventory);
        log.debug("[{}][getPriceAndInventory] -> response:{}", this.getClass().getSimpleName(), priceAndInventoryResponse);
        return InternalApiResponse.<PriceAndInventoryResponse>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(priceAndInventoryResponse)
                .build();

    }


    private static PriceAndInventoryResponse convertProductResponse(PriceAndInventory priceAndInventory) {
        return PriceAndInventoryResponse.builder()
                .barcode(priceAndInventory.getBarcode())
                .userId(priceAndInventory.getUserId())
                .listPrice(priceAndInventory.getListPrice())
                .salePrice(priceAndInventory.getSalePrice())
                .quantity(priceAndInventory.getQuantity())
                .build();
    }

}
