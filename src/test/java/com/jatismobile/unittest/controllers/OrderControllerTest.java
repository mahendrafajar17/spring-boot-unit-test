package com.jatismobile.unittest.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import com.jatismobile.unittest.dtos.OrderRequestData;
import com.jatismobile.unittest.dtos.OrderResponseData;
import com.jatismobile.unittest.services.unittest.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Mock
    OrderService orderService;

    OrderController orderController;

    ModelMapper modelMapper = new ModelMapper();

    Random random = new Random();

    @Test
    public void postRequest() {
        OrderRequestData orderRequestData = new OrderRequestData();
        orderRequestData.setId(1);
        orderRequestData.setItemId(1);
        orderRequestData.setBuyerName("Buyer 1");

        orderController = new OrderController(orderService);
        when(orderService.save(any())).thenReturn(new OrderResponseData());

        ResponseEntity<Object> responseEntity = orderController.postRequest(orderRequestData);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void putRequest() {
        OrderRequestData orderRequestData = new OrderRequestData();
        orderRequestData.setId(1);
        orderRequestData.setItemId(1);
        orderRequestData.setBuyerName("Buyer 1");

        orderController = new OrderController(orderService);
        when(orderService.save(any())).thenReturn(new OrderResponseData());

        ResponseEntity<Object> responseEntity = orderController.putRequest(orderRequestData);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
    }
}
