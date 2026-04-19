package com.microservice.ecom_order_service.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.UUID;

@Service
public class OrderService {

    private  final RestTemplate restTemplate;
    private final RestClient restClient;

    public OrderService(RestTemplate restTemplate, RestClient restClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }

    public String placeOrder(long productId){

 //       String response = restTemplate.getForObject("http://localhost:8081/inventory/"+productId, String.class);

        ResponseEntity<String> res = restClient.get()
                .uri("http://localhost:8081/inventory/"+productId)
                .retrieve().toEntity(String.class);
        System.out.println(res.getBody());
        return "IN STOCK".equalsIgnoreCase(res.getBody()) ? "Order Placed "+ UUID.randomUUID() : "Product is out of stock";

    }
}
