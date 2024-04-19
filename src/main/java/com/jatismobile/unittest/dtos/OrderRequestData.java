package com.jatismobile.unittest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderRequestData {
    Integer id;
    
    @JsonProperty("item_id")
    Integer itemId;
    
    @JsonProperty("buyer_name")
    String buyerName;

    String date;
}
