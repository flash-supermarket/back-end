package com.flash_supermarket.flash_supermarket.utils;

import lombok.Getter;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/5/27 20:36
 */

@Getter
public class BusinessException extends Exception{

    int code;
    String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
