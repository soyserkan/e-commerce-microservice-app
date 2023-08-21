package com.integration.productservice.repository;
import com.integration.productservice.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getByIdAndDeletedFalse(Long productId);
    Product getByBarcodeAndDeletedFalse(String barcode);
    List<Product> getAllByDeletedFalse();
}
