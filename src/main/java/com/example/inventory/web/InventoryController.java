package com.example.inventory.web;

import com.example.inventory.dto.CreateInventoryRequest;
import com.example.inventory.dto.InventoryItemResponse;
import com.example.inventory.dto.UpdateInventoryRequest;
import com.example.inventory.service.InventoryService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Inventory", description = "Inventory item CRUD (rate-limited and bulkhead-throttled)")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @RateLimiter(name = "inventoryRead")
    @Bulkhead(name = "inventoryApi")
    @Operation(summary = "List all inventory items")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "429", description = "Rate limit exceeded")
    @ApiResponse(responseCode = "503", description = "Bulkhead full (too many concurrent calls)")
    public List<InventoryItemResponse> list() {
        return inventoryService.listAll();
    }

    @GetMapping("/{id}")
    @RateLimiter(name = "inventoryRead")
    @Bulkhead(name = "inventoryApi")
    @Operation(summary = "Get item by id (MongoDB ObjectId hex string)")
    public InventoryItemResponse get(@PathVariable String id) {
        return inventoryService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @RateLimiter(name = "inventoryWrite")
    @Bulkhead(name = "inventoryApi")
    @Operation(summary = "Create inventory item")
    public InventoryItemResponse create(@Valid @RequestBody CreateInventoryRequest body) {
        return inventoryService.create(body);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @RateLimiter(name = "inventoryWrite")
    @Bulkhead(name = "inventoryApi")
    @Operation(summary = "Update inventory item")
    public InventoryItemResponse update(@PathVariable String id, @Valid @RequestBody UpdateInventoryRequest body) {
        return inventoryService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RateLimiter(name = "inventoryWrite")
    @Bulkhead(name = "inventoryApi")
    @Operation(summary = "Delete inventory item")
    public void delete(@PathVariable String id) {
        inventoryService.delete(id);
    }
}
