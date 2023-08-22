package com.dev.inventoryservice.repository;

import com.dev.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByName(String name);

    Inventory findByNameAndIsStockIsTrue(String name);
}
