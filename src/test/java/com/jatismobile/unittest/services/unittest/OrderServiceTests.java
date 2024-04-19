package com.jatismobile.unittest.services.unittest;

import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.jatismobile.unittest.dtos.OrderRequestData;
import com.jatismobile.unittest.dtos.OrderResponseData;
import com.jatismobile.unittest.models.unittest.OrderModel;
import com.jatismobile.unittest.repositories.unittest.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {
    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderService orderService;

    ModelMapper modelMapper = new ModelMapper();

    Random random = new Random();

    @Test
    public void saveNewData() {
        OrderRequestData orderRequestData = new OrderRequestData();
        orderRequestData.setItemId(1);
        orderRequestData.setBuyerName("Buyer 1");

        OrderModel orderModel = new OrderModel();
        orderModel.setId(random.nextInt());
        orderModel.setItemId(orderRequestData.getItemId());
        orderModel.setBuyerName(orderRequestData.getBuyerName());
        orderModel.setCreatedAt(new Date());
        Mockito.when(orderRepository.save(any())).thenReturn(orderModel);
        orderService = new OrderService(modelMapper, orderRepository);

        OrderResponseData orderResponseData = orderService.save(orderRequestData);

        Assertions.assertNotNull(orderResponseData.getId());
        Assertions.assertEquals(orderRequestData.getBuyerName(), orderResponseData.getBuyerName());
    }

    @Test
    public void saveUpdateData() {
        OrderRequestData orderRequestData = new OrderRequestData();
        orderRequestData.setId(1);
        orderRequestData.setItemId(1);
        orderRequestData.setBuyerName("Buyer 1");

        OrderModel orderModel = new OrderModel();
        orderModel.setId(orderRequestData.getId());
        orderModel.setItemId(orderRequestData.getItemId());
        orderModel.setBuyerName(orderRequestData.getBuyerName());
        orderModel.setCreatedAt(new Date());
        Mockito.when(orderRepository.save(any())).thenReturn(orderModel);
        orderService = new OrderService(modelMapper, orderRepository);

        OrderResponseData orderResponseData = orderService.save(orderRequestData);
        
        Assertions.assertEquals(orderRequestData.getId(), orderResponseData.getId());
        Assertions.assertEquals(orderRequestData.getBuyerName(), orderResponseData.getBuyerName());
    }
}
