package com.microservice.ecom_order_service.controller;

import com.microservice.ecom_order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/{productId}")
    public String placeOrder(@PathVariable long productId){
        return orderService.placeOrder(productId);
    }
}
