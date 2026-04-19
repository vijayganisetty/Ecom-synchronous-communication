package com.microservice.ecom_inventory_service.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    private  Map<String, Integer> inventory;

    @PostConstruct
    public void populate() {
        inventory = new HashMap<>();
        inventory.put("1001",2);
    }

    public boolean checkInventory(String productId){
        if(inventory.containsKey(productId) && inventory.get(productId)>0){
            inventory.replace(productId, inventory.get(productId)-1);
            return true;
        }
        return false;
    }
}
