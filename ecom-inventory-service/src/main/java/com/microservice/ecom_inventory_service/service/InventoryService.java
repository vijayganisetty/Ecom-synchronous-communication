package com.microservice.ecom_inventory_service.service;

import com.microservice.ecom_inventory_service.model.Inventory;
import com.microservice.ecom_inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public String checkInventory(long productId){

       Inventory inv =  inventoryRepository.findById(productId).orElse(null);
       if(null !=inv && inv.getQuantity()>0){
           inv.setQuantity(inv.getQuantity()-1);
           inventoryRepository.save(inv);
           return "IN STOCK";
       }
        return "OUT OF STOCK";
    }

    public  Inventory addProduct(Inventory inventory){
      return inventoryRepository.save(inventory);
    }
}
