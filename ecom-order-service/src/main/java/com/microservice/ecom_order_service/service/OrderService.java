package com.microservice.ecom_order_service.service;

import com.microservice.ecom_order_service.client.InventoryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.UUID;

@Service
public class OrderService {

    private final InventoryClient inventoryClient;
    private  final RestTemplate restTemplate;
    private final RestClient restClient;

    public OrderService(InventoryClient inventoryClient, RestTemplate restTemplate, RestClient restClient) {
        this.inventoryClient = inventoryClient;
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }

    public String placeOrder(long productId){

 //       String response = restTemplate.getForObject("http://localhost:8081/inventory/"+productId, String.class);


         String res = inventoryClient.checkInventory(productId);

//        ResponseEntity<String> res = restClient.post()
//                .uri("http://localhost:8081/inventory/"+productId)
//                .retrieve().toEntity(String.class);
//        System.out.println(res.getBody());
        return "IN STOCK".equalsIgnoreCase(res) ? "Order Placed "+ UUID.randomUUID() : "Product is out of stock";
    }
}
