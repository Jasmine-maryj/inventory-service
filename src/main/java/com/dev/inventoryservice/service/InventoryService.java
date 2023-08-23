package com.dev.inventoryservice.service;

import com.dev.inventoryservice.dto.InventoryResponse;
import com.dev.inventoryservice.dto.ProductDto;
import com.dev.inventoryservice.dto.UpdateInventoryQuantityDto;


import java.util.List;

public interface InventoryService {
    void addProduct(ProductDto productDto);

    InventoryResponse getInventoryByName(String name);

    List<InventoryResponse> isInStock(List<String> names);

    List<InventoryResponse> getAllAvailableProducts();

    List<InventoryResponse> getProductByName(List<String> names);

    String updateInventoryStock(List<UpdateInventoryQuantityDto> updateInventoryQuantityDtoList);
}
