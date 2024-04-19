package com.jatismobile.unittest.dtos;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ItemRequestData {
    Integer id;
    
    @NotEmpty(message = "Name is required")
    String name;
}
