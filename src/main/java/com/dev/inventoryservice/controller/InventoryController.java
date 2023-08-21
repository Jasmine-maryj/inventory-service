package com.dev.inventoryservice.controller;

import com.dev.inventoryservice.dto.ProductDto;
import com.dev.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
