package com.example.inventory.service;

import com.example.inventory.dto.CreateInventoryRequest;
import com.example.inventory.dto.InventoryItemResponse;
import com.example.inventory.dto.UpdateInventoryRequest;
import com.example.inventory.exception.NotFoundException;
import com.example.inventory.model.InventoryItemDocument;
import com.example.inventory.repository.InventoryItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryItemRepository repository;

    public InventoryService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public List<InventoryItemResponse> listAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(InventoryMapper::toResponse)
                .toList();
    }

    public InventoryItemResponse getById(String id) {
        InventoryItemDocument item = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found: " + id));
        return InventoryMapper.toResponse(item);
    }

    public InventoryItemResponse create(CreateInventoryRequest request) {
        String sku = request.getSku().trim();
        if (repository.findBySku(sku).isPresent()) {
            throw new IllegalArgumentException("SKU already exists: " + sku);
        }
        InventoryItemDocument saved = repository.save(InventoryMapper.fromCreate(request));
        return InventoryMapper.toResponse(saved);
    }

    public InventoryItemResponse update(String id, UpdateInventoryRequest request) {
        InventoryItemDocument item = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found: " + id));
        InventoryMapper.applyUpdate(item, request);
        repository.save(item);
        return InventoryMapper.toResponse(item);
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Item not found: " + id);
        }
        repository.deleteById(id);
    }
}
