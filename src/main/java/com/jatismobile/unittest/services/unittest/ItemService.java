package com.jatismobile.unittest.services.unittest;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jatismobile.unittest.dtos.ItemRequestData;
import com.jatismobile.unittest.dtos.ItemResponseData;
import com.jatismobile.unittest.models.unittest.ItemModel;
import com.jatismobile.unittest.repositories.unittest.ItemRepository;
import com.jatismobile.unittest.utils.DateTimeUtil;

@Service
public class ItemService {
    ItemRepository itemRepository;
    ModelMapper modelMapper;

    public ItemService(ModelMapper modelMapper, ItemRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    /**
     * Mapping ItemRequestData to ItemModel.
     * Save new data if the property id of ItemRequestData is null and update data
     * and if the property id of ItemRequestData is not null.
     * ItemResponseData mapped by ItemModel and property created_at of
     * ItemResponseData converted to string date.
     * 
     * @param itemRequestData
     * @return ItemResponseData
     */
    public ItemResponseData save(ItemRequestData itemRequestData) {
        ItemModel itemModel = modelMapper.map(itemRequestData, ItemModel.class);
        if (itemRequestData.getId() != null) {
            itemModel.setId(itemRequestData.getId());
        }
        itemModel.setCreatedAt(new Date());
        itemModel = itemRepository.save(itemModel);

        ItemResponseData itemResponseData = modelMapper.map(itemModel, ItemResponseData.class);
        itemResponseData
                .setCreatedAt(DateTimeUtil.dateToString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, itemModel.getCreatedAt()));
        return itemResponseData;
    }
}
