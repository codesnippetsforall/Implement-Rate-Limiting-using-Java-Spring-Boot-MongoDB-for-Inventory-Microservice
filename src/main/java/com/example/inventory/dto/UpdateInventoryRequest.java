package com.example.inventory.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateInventoryRequest {

    @Size(max = 255)
    private String name;

    @Min(0)
    private Integer quantity;

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal unitPrice;
}
