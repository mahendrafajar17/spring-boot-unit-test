package com.jatismobile.unittest.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatismobile.unittest.dtos.ItemRequestData;
import com.jatismobile.unittest.dtos.ItemResponseData;
import com.jatismobile.unittest.services.unittest.ItemService;
import com.jatismobile.unittest.utils.ValidationUtil;

@RestController
@RequestMapping("${app.path.item}")
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    ResponseEntity<Object> postRequest(@RequestBody @Valid ItemRequestData itemRequestData, Errors errors) {
        ValidationUtil.validationBodyRequest(errors);
        ItemResponseData itemResponseData = itemService.save(itemRequestData);
        return ResponseEntity.status(HttpStatus.OK).body(itemResponseData);
    }

    @PutMapping
    ResponseEntity<Object> putRequest(@RequestBody ItemRequestData itemRequestData) {
        ItemResponseData itemResponseData = itemService.save(itemRequestData);
        return ResponseEntity.status(HttpStatus.OK).body(itemResponseData);
    }
}
