package com.jatismobile.unittest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatismobile.unittest.dtos.OrderRequestData;
import com.jatismobile.unittest.dtos.OrderResponseData;
import com.jatismobile.unittest.services.unittest.OrderService;

@RestController
@RequestMapping("${app.path.order}")
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    ResponseEntity<Object> postRequest(@RequestBody OrderRequestData orderRequestData) {
        OrderResponseData orderResponseData = orderService.save(orderRequestData);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseData);
    }

    @PutMapping
    ResponseEntity<Object> putRequest(@RequestBody OrderRequestData orderRequestData) {
        OrderResponseData orderResponseData = orderService.save(orderRequestData);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseData);
    }
}
