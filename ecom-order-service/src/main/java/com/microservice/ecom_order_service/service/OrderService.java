package com.microservice.ecom_order_service.service;

import com.microservice.ecom_order_service.client.InventoryClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.UUID;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final InventoryClient inventoryClient;
    private  final RestTemplate restTemplate;
    private final RestClient restClient;

    public OrderService(InventoryClient inventoryClient, RestTemplate restTemplate, RestClient restClient) {
        this.inventoryClient = inventoryClient;
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }

//    @Retryable(
//            retryFor = RuntimeException.class,
//            maxAttempts = 3,
//            backoff = @Backoff(delay = 20000)
//    )
//    @RateLimiter(name = "orderService", fallbackMethod = "fallbackMethod")
    @CircuitBreaker(name = "inventoryServiceCircuitBreaker", fallbackMethod = "fallbackMethodCircuitBreaker" )
    public String placeOrder(long productId){

 //       String response = restTemplate.getForObject("http://localhost:8081/inventory/"+productId, String.class);


         String res = inventoryClient.checkInventory(productId);

//        ResponseEntity<String> res = restClient.post()
//                .uri("http://localhost:8081/inventory/"+productId)
//                .retrieve().toEntity(String.class);
//        System.out.println(res.getBody());
        return "IN STOCK".equalsIgnoreCase(res) ? "Order Placed "+ UUID.randomUUID() : "Product is out of stock";
    }
    public String fallbackMethod(long productId, Throwable throwable){
        log.info("fallback executed");
        String res = inventoryClient.checkInventory(productId);
        return "IN STOCK".equalsIgnoreCase(res) ? "Order Placed "+ UUID.randomUUID() : "Product is out of stock";
    }
    public String fallbackMethodCircuitBreaker(long productId, Throwable throwable){
        log.info("fallback executed");
        String res = inventoryClient.checkInventory(productId);
        return "IN STOCK".equalsIgnoreCase(res) ? "Order Placed "+ UUID.randomUUID() : "Product is out of stock";
    }
}
