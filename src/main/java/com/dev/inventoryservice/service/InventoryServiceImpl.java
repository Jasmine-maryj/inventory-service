package com.dev.inventoryservice.service;

import com.dev.inventoryservice.dto.InventoryResponse;
import com.dev.inventoryservice.dto.ProductDto;
import com.dev.inventoryservice.dto.UpdateInventoryQuantityDto;
import com.dev.inventoryservice.entity.Inventory;
import com.dev.inventoryservice.handler.ResourceNotFoundException;
import com.dev.inventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void addProduct(ProductDto productDto) {
        Inventory inventory = new Inventory();

        inventory.setName(productDto.getName());
        inventory.setQuantity(productDto.getQuantity());

        int productQuantity = productDto.getQuantity();
        inventory.setStock(productQuantity > 0);

        inventoryRepository.save(inventory);
    }

    @Override
    public InventoryResponse getInventoryByName(String name){
        Inventory inventory = inventoryRepository.findByName(name);
        if(inventory == null){
            throw new ResourceNotFoundException("Resource Not Found!!");
        }
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setName(inventory.getName());
        inventoryResponse.setQuantity(inventory.getQuantity());
        inventoryResponse.setStock(inventory.isStock());
        return inventoryResponse;
    }

    @Override
    public List<InventoryResponse> isInStock(List<String> names) {
        List<InventoryResponse> availableInventoryResponses = new ArrayList<>();

        for (String name : names) {
            Inventory inventory = inventoryRepository.findByNameAndIsStockIsTrue(name);
            if (inventory != null) {
                InventoryResponse response = new InventoryResponse();
                response.setName(inventory.getName());
                response.setQuantity(inventory.getQuantity());
                response.setStock(inventory.isStock());
                availableInventoryResponses.add(response);
            }
        }

        return availableInventoryResponses;

    }

    public List<InventoryResponse> getProductByName(List<String> names) {
        List<InventoryResponse> availableInventoryResponses = new ArrayList<>();

        for (String name : names) {
            Inventory inventory = inventoryRepository.findByName(name);
            if (inventory != null) {
                InventoryResponse response = new InventoryResponse();
                response.setName(inventory.getName());
                response.setQuantity(inventory.getQuantity());
                response.setStock(inventory.isStock());
                availableInventoryResponses.add(response);
            }
        }

        return availableInventoryResponses;

    }

    @Override
    public String updateInventoryStock(List<UpdateInventoryQuantityDto> updateInventoryQuantityDtoList) {
        for(UpdateInventoryQuantityDto dto: updateInventoryQuantityDtoList){
            String name = dto.getName();
            int quantity = dto.getQuantity();

            boolean updateStatus = updateInventoryStock(name, quantity);

            if(!updateStatus){
                return "Update was failed for the product:" + name;
            }
        }
        return "Updated Successfully!";
    }

    private boolean updateInventoryStock(String name, int quantity) {
        try {
            Inventory inventory = inventoryRepository.findByName(name);
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
            return true;
        }catch (Exception exception){
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    @Override
    public List<InventoryResponse> getAllAvailableProducts() {
        List<Inventory> inventories = inventoryRepository.findAll();

        return inventories.stream()
                .map(inventory -> {
                    InventoryResponse inventoryResponse = new InventoryResponse();
                    inventoryResponse.setName(inventory.getName());
                    inventoryResponse.setQuantity(inventory.getQuantity());
                    inventoryResponse.setStock(inventory.isStock());
                    return inventoryResponse;
                }).toList();
    }
}
