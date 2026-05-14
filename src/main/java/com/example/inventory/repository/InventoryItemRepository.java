package com.example.inventory.repository;

import com.example.inventory.model.InventoryItemDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InventoryItemRepository extends MongoRepository<InventoryItemDocument, String> {

    Optional<InventoryItemDocument> findBySku(String sku);
}
