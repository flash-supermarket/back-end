package com.flash_supermarket.flash_supermarket.service;

import com.flash_supermarket.flash_supermarket.dao.User;
import com.flash_supermarket.flash_supermarket.dto.UserDTO;
import com.flash_supermarket.flash_supermarket.utils.BusinessException;

public interface UserService {
    void register(UserDTO userDTO) throws BusinessException;
    void login(UserDTO userDTO) throws  BusinessException;
}
