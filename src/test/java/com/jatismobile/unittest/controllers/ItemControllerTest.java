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
import org.springframework.validation.Errors;

import com.jatismobile.unittest.dtos.ItemRequestData;
import com.jatismobile.unittest.dtos.ItemResponseData;
import com.jatismobile.unittest.services.unittest.ItemService;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    @Mock
    ItemService itemService;

    ItemController itemController;

    ModelMapper modelMapper = new ModelMapper();

    Random random = new Random();

    @Mock
    Errors errors;

    @Test
    public void postRequest() {
        ItemRequestData itemRequestData = new ItemRequestData();
        itemRequestData.setName("Item 1");

        itemController = new ItemController(itemService);
        when(itemService.save(any())).thenReturn(new ItemResponseData());
        when(errors.hasErrors()).thenReturn(false);

        ResponseEntity<Object> responseEntity = itemController.postRequest(itemRequestData, errors);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void putRequest() {
        ItemRequestData itemRequestData = new ItemRequestData();
        itemRequestData.setName("Item 1");

        itemController = new ItemController(itemService);
        when(itemService.save(any())).thenReturn(new ItemResponseData());

        ResponseEntity<Object> responseEntity = itemController.putRequest(itemRequestData);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
    }
}
