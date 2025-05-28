package com.flash_supermarket.flash_supermarket.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/5/27 20:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private int code = 200;
    private String message = "OK";
    private Object data;
}

