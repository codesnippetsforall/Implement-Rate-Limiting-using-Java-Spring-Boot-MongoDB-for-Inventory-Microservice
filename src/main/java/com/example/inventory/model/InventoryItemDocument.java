package com.example.inventory.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Document(collection = "inventory-items")
public class InventoryItemDocument {

    @Id
    private String id;

    @Indexed(unique = true)
    private String sku;

    private String name;

    private int quantity;

    private BigDecimal unitPrice;

    private Instant createdAt;

    private Instant updatedAt;
}
