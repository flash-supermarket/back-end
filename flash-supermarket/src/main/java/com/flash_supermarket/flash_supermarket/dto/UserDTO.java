package com.flash_supermarket.flash_supermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/5/27 21:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private String passWord;
}

