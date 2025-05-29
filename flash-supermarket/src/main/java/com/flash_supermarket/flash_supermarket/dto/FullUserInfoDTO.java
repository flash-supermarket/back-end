package com.flash_supermarket.flash_supermarket.dto;

import com.flash_supermarket.flash_supermarket.dao.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/5/29 14:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullUserInfoDTO {
    private String userName;
    private String description;
    private String avatar;
    private String passWord;
    private List<User> follows;
    private List<User> fans;
}

