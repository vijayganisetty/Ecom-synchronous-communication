package com.microservice.ecom_inventory_service.controller;

import com.microservice.ecom_inventory_service.model.Inventory;
import com.microservice.ecom_inventory_service.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping()
    public String addInventory(@RequestBody Inventory inventory){
        if(inventory.getProductId().equals(inventoryService.addProduct(inventory).getProductId())){
            return "Product Added Successfully ";
        }
        return "Failed add product";
    }

    @GetMapping("/{productId}")
    public String checkInventory(@PathVariable long productId){
      return inventoryService.checkInventory(productId);
    }

}
