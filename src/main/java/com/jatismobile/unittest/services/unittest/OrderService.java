package com.jatismobile.unittest.services.unittest;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jatismobile.unittest.dtos.OrderRequestData;
import com.jatismobile.unittest.dtos.OrderResponseData;
import com.jatismobile.unittest.models.unittest.OrderModel;
import com.jatismobile.unittest.repositories.unittest.OrderRepository;
import com.jatismobile.unittest.utils.DateTimeUtil;

@Service
public class OrderService {
    OrderRepository orderRepository;
    ModelMapper modelMapper;

    public OrderService(ModelMapper modelMapper, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    /**
     * Mapping orderRequestData to ItemModel.
     * Save new data if the property id of orderRequestData is null and update data
     * and if the property id of orderRequestData is not null.
     * OrderResponseData mapped by ItemModel and property created_at of
     * OrderResponseData converted to string date.
     * 
     * @param orderRequestData
     * @return OrderResponseData
     */
    public OrderResponseData save(OrderRequestData orderRequestData) {
        OrderModel orderModel = modelMapper.map(orderRequestData, OrderModel.class);
        if (orderRequestData.getId() != null) {
            orderModel.setId(orderRequestData.getId());
        }
        orderModel.setCreatedAt(new Date());
        orderModel = orderRepository.save(orderModel);

        OrderResponseData orderResponseData = modelMapper.map(orderModel, OrderResponseData.class);
        orderResponseData
                .setCreatedAt(DateTimeUtil.dateToString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, orderModel.getCreatedAt()));
        return orderResponseData;
    }
}
