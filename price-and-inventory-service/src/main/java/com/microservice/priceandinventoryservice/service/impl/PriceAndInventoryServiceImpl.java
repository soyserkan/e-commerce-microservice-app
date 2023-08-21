package com.microservice.priceandinventoryservice.service.impl;

import com.microservice.priceandinventoryservice.client.ProductClient;
import com.microservice.priceandinventoryservice.dto.ProductResponse;
import com.microservice.priceandinventoryservice.enums.Language;
import com.microservice.priceandinventoryservice.exception.enums.MessageCodes;
import com.microservice.priceandinventoryservice.exception.exceptions.ProductNotFoundException;
import com.microservice.priceandinventoryservice.repository.PriceAndInventoryRepository;
import com.microservice.priceandinventoryservice.repository.entity.PriceAndInventory;
import com.microservice.priceandinventoryservice.service.IPriceAndInventoryService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PriceAndInventoryServiceImpl implements IPriceAndInventoryService {

    private final PriceAndInventoryRepository priceAndInventoryRepository;
    private final ProductClient productClient;

    @Override
    public PriceAndInventory getPriceAndInventory(String barcode) {
        PriceAndInventory priceAndInventory;
        try {
            Optional<PriceAndInventory> optionalProduct = priceAndInventoryRepository.findById(barcode);
            if (optionalProduct.isPresent()) {
                priceAndInventory = optionalProduct.get();
            } else {
                priceAndInventory = new PriceAndInventory();
                ProductResponse response = productClient.getProduct(barcode).getPayload();
                log.info("data came from product-service");

                priceAndInventory.setBarcode(response.getBarcode());
                priceAndInventory.setQuantity(response.getQuantity());
                priceAndInventory.setListPrice(response.getListPrice());
                priceAndInventory.setSalePrice(response.getSalePrice());
                priceAndInventoryRepository.save(priceAndInventory);
            }
        }  catch (Exception exception) {
            throw new ProductNotFoundException(Language.EN, MessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "Products not found");
        }
        return priceAndInventory;
    }
}
