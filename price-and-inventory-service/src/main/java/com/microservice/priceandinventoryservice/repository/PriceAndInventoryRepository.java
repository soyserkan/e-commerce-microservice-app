package com.microservice.priceandinventoryservice.repository;

import com.microservice.priceandinventoryservice.repository.entity.PriceAndInventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceAndInventoryRepository extends CrudRepository<PriceAndInventory, String> {
}
