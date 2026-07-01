package com.example.inventory.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class InventoryItemResponse {

    private String id;
    private String sku;
    private String name;
    private int quantity;
    private BigDecimal unitPrice;
    private Instant createdAt;
    private Instant updatedAt;
}
