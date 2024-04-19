package com.jatismobile.unittest.dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ItemResponseData {
    Integer id;
    String name;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("updated_at")
    String updatedAt;
}
