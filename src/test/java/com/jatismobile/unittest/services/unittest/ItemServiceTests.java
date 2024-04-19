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

import com.jatismobile.unittest.dtos.ItemRequestData;
import com.jatismobile.unittest.dtos.ItemResponseData;
import com.jatismobile.unittest.models.unittest.ItemModel;
import com.jatismobile.unittest.repositories.unittest.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTests {
    @Mock
    ItemRepository itemRepository;

    @Mock
    ItemService itemService;

    ModelMapper modelMapper = new ModelMapper();

    Random random = new Random();

    @Test
    public void saveNewData() {
        ItemRequestData itemRequestData = new ItemRequestData();
        itemRequestData.setName("Item 1");

        ItemModel itemModel = new ItemModel();
        itemModel.setId(random.nextInt());
        itemModel.setName(itemRequestData.getName());
        itemModel.setCreatedAt(new Date());
        Mockito.when(itemRepository.save(any())).thenReturn(itemModel);
        itemService = new ItemService(modelMapper, itemRepository);

        ItemResponseData itemResponseData = itemService.save(itemRequestData);

        Assertions.assertNotNull(itemResponseData.getId());
        Assertions.assertEquals(itemRequestData.getName(), itemResponseData.getName());
    }

    @Test
    public void saveUpdateData() {
        ItemRequestData itemRequestData = new ItemRequestData();
        itemRequestData.setId(1);
        itemRequestData.setName("Item 1");

        ItemModel itemModel = new ItemModel();
        itemModel.setId(itemRequestData.getId());
        itemModel.setName(itemRequestData.getName());
        itemModel.setCreatedAt(new Date());
        Mockito.when(itemRepository.save(any())).thenReturn(itemModel);
        itemService = new ItemService(modelMapper, itemRepository);

        ItemResponseData itemResponseData = itemService.save(itemRequestData);

        Assertions.assertEquals(itemRequestData.getId(), itemResponseData.getId());
        Assertions.assertEquals(itemRequestData.getName(), itemResponseData.getName());
    }
}
