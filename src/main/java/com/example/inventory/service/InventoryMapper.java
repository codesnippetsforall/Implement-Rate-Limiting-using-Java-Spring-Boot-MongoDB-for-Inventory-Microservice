package com.example.inventory.service;

import com.example.inventory.dto.CreateInventoryRequest;
import com.example.inventory.dto.InventoryItemResponse;
import com.example.inventory.dto.UpdateInventoryRequest;
import com.example.inventory.model.InventoryItemDocument;

import java.time.Instant;

final class InventoryMapper {

    private InventoryMapper() {
    }

    static InventoryItemResponse toResponse(InventoryItemDocument item) {
        InventoryItemResponse r = new InventoryItemResponse();
        r.setId(item.getId());
        r.setSku(item.getSku());
        r.setName(item.getName());
        r.setQuantity(item.getQuantity());
        r.setUnitPrice(item.getUnitPrice());
        r.setCreatedAt(item.getCreatedAt());
        r.setUpdatedAt(item.getUpdatedAt());
        return r;
    }

    static InventoryItemDocument fromCreate(CreateInventoryRequest req) {
        InventoryItemDocument item = new InventoryItemDocument();
        item.setSku(req.getSku().trim());
        item.setName(req.getName().trim());
        item.setQuantity(req.getQuantity());
        item.setUnitPrice(req.getUnitPrice());
        Instant now = Instant.now();
        item.setCreatedAt(now);
        item.setUpdatedAt(now);
        return item;
    }

    static void applyUpdate(InventoryItemDocument item, UpdateInventoryRequest req) {
        if (req.getName() != null) {
            item.setName(req.getName().trim());
        }
        if (req.getQuantity() != null) {
            item.setQuantity(req.getQuantity());
        }
        if (req.getUnitPrice() != null) {
            item.setUnitPrice(req.getUnitPrice());
        }
        item.setUpdatedAt(Instant.now());
    }
}
