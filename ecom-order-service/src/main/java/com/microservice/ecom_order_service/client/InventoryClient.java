package com.microservice.ecom_order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "inventory-service", url ="http://localhost:8081")
public interface InventoryClient {

    @PostMapping("/inventory/{productId}")
    String checkInventory(@PathVariable long productId);
}
