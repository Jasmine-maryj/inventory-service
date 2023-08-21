package com.dev.inventoryservice.service;

import com.dev.inventoryservice.dto.ProductDto;

public interface InventoryService {
    void addProduct(ProductDto productDto);
}
