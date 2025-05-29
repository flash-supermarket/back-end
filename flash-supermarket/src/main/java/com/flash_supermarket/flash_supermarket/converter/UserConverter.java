package com.flash_supermarket.flash_supermarket.converter;

import com.flash_supermarket.flash_supermarket.dao.User;
import com.flash_supermarket.flash_supermarket.dto.FullUserInfoDTO;
import com.flash_supermarket.flash_supermarket.dto.UserDTO;

import java.util.List;

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

    public static FullUserInfoDTO userConverter(User user, List<User> follows, List<User> fans){
        FullUserInfoDTO fullUserInfoDTO = new FullUserInfoDTO();
        fullUserInfoDTO.setUserName(user.getUserName());
        fullUserInfoDTO.setDescription(user.getDescription());
        fullUserInfoDTO.setFans(fans);
        fullUserInfoDTO.setFollows(follows);
        fullUserInfoDTO.setAvatar(user.getAvatar());
        fullUserInfoDTO.setPassWord(user.getPassWord());
        return fullUserInfoDTO;
    }
}

