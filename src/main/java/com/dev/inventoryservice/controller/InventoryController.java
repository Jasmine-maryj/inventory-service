package com.dev.inventoryservice.controller;

import com.dev.inventoryservice.dto.InventoryResponse;
import com.dev.inventoryservice.dto.ProductDto;
import com.dev.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto){
        inventoryService.addProduct(productDto);
        return new ResponseEntity<>("Product Added", HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<InventoryResponse> getInventoryByName(@PathVariable String name){
        InventoryResponse inventory = inventoryService.getInventoryByName(name);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/available-products")
    public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam List<String> names){
        List<InventoryResponse> stockList = inventoryService.isInStock(names);
        return ResponseEntity.ok(stockList);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<InventoryResponse>> getAllAvailableProducts(){
        List<InventoryResponse> inventoryResponses = inventoryService.getAllAvailableProducts();
        return ResponseEntity.ok(inventoryResponses);
    }
}
