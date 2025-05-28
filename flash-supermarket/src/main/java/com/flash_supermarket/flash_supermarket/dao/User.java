package com.flash_supermarket.flash_supermarket.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/5/27 21:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String description;
    private String avatar;
    private String passWord;
}

