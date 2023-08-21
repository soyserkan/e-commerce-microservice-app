package com.microservice.priceandinventoryservice.service;

import com.microservice.priceandinventoryservice.repository.entity.PriceAndInventory;

public interface IPriceAndInventoryService {
    PriceAndInventory getPriceAndInventory(String barcode);
}
