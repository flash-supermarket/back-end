package com.flash_supermarket.flash_supermarket.controller;

import com.flash_supermarket.flash_supermarket.dto.UserDTO;
import com.flash_supermarket.flash_supermarket.response.CustomResponse;
import com.flash_supermarket.flash_supermarket.service.UserService;
import com.flash_supermarket.flash_supermarket.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/5/27 21:01
 */
@CrossOrigin
@RestController
@Tag(name = "用户", description = "用户管理")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    // 注册
    @PostMapping("/register")
    @Operation(summary = "注册", description = "用户注册，传入 userName 和 passWord")
    public CustomResponse register(@RequestBody UserDTO userDTO){
        CustomResponse customResponse = new CustomResponse();
        try {
            userService.register(userDTO);
            customResponse.setMessage("注册成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 登录
    @PostMapping("/login")
    @Operation(summary = "登录", description = "用户登录，传入 userName 与 passWord")
    public CustomResponse login(@RequestBody UserDTO userDTO){
        CustomResponse customResponse = new CustomResponse();
        try {
            userService.login(userDTO);
            customResponse.setMessage("登录成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}

