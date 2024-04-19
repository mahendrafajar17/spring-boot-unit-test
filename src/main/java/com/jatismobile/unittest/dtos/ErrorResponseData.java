package com.jatismobile.unittest.dtos;

import lombok.Data;

@Data
public class ErrorResponseData {
    Integer code;
    String title;
    String detail;
}
