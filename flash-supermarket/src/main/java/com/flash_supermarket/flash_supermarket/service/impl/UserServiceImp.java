package com.flash_supermarket.flash_supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash_supermarket.flash_supermarket.dao.User;
import com.flash_supermarket.flash_supermarket.dto.UserDTO;
import com.flash_supermarket.flash_supermarket.mapper.UserMapper;
import com.flash_supermarket.flash_supermarket.service.UserService;
import com.flash_supermarket.flash_supermarket.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/5/27 21:52
 */
@Slf4j
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(UserDTO userDTO) throws BusinessException {
        String name = userDTO.getUserName();
        String passWord = userDTO.getPassWord();
        if (name == null){
            throw new BusinessException(400, "用户名为空");
        }
        if (passWord == null){
            throw new BusinessException(400, "密码为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", name);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null){
            throw new BusinessException(400, "用户名已存在");
        }
        User newUser = new User(name, null, null, passWord);
        userMapper.insert(newUser);
    }

    @Override
    public void login(UserDTO userDTO) throws BusinessException {
        String name = userDTO.getUserName();
        String passWord = userDTO.getPassWord();
        if (name == null){
            throw new BusinessException(400, "用户名为空");
        }
        if (passWord == null){
            throw new BusinessException(400, "密码为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", name);
        queryWrapper.eq("pass_word", passWord);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BusinessException(400, "用户名不存在或密码错误");
        }
    }
}

