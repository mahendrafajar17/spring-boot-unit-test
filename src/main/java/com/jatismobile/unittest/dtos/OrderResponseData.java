package com.jatismobile.unittest.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OrderResponseData {
    Integer id;

    @SerializedName("buyer_name")
    String buyerName;

    String date;

    @SerializedName("created_at")
    String createdAt;
    
    @SerializedName("updated_at")
    String updatedAt;
}
