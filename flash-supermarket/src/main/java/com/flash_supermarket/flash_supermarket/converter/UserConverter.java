package com.flash_supermarket.flash_supermarket.converter;

import com.flash_supermarket.flash_supermarket.dao.User;
import com.flash_supermarket.flash_supermarket.dto.UserDTO;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/5/27 21:47
 */
public class UserConverter {
    public static UserDTO userConverter(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setPassWord(user.getPassWord());
        return userDTO;
    }
}

