package com.dev.inventoryservice.service;

import com.dev.inventoryservice.dto.ProductDto;
import com.dev.inventoryservice.entity.Inventory;
import com.dev.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void addProduct(ProductDto productDto) {
        Inventory inventory = new Inventory();

        inventory.setProductName(productDto.getProductName());
        inventory.setQuantity(productDto.getQuantity());

        int productQuantity = productDto.getQuantity();
        inventory.setStock(productQuantity > 0);

        inventoryRepository.save(inventory);
    }
}
