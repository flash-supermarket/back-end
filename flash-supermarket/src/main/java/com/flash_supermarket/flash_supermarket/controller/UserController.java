package com.flash_supermarket.flash_supermarket.controller;

import com.flash_supermarket.flash_supermarket.dao.Relationship;
import com.flash_supermarket.flash_supermarket.dao.User;
import com.flash_supermarket.flash_supermarket.dto.UserDTO;
import com.flash_supermarket.flash_supermarket.response.CustomResponse;
import com.flash_supermarket.flash_supermarket.service.UserService;
import com.flash_supermarket.flash_supermarket.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
            customResponse.setData(true);
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
            customResponse.setData(true);
            customResponse.setMessage("登录成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 编辑信息
    @PostMapping("/edit")
    @Operation(summary = "编辑个人信息", description = "可修改用户描述，用户头像和密码")
    public CustomResponse edit(@RequestBody User user){
        CustomResponse customResponse = new CustomResponse();
        try {
            userService.edit(user);
            customResponse.setData(true);
            customResponse.setMessage("修改成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 关注别人
    @PostMapping("/follow")
    @Operation(summary = "关注其它用户", description = "关注用户，传入关注的用户的 userName ")
    public CustomResponse follow(@RequestBody Relationship relationship){
        CustomResponse customResponse = new CustomResponse();
        try {
            userService.follow(relationship);
            customResponse.setData(true);
            customResponse.setMessage("关注成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 取消关注别人
    @DeleteMapping("/unfollow")
    @Operation(summary = "取消关注用户")
    public CustomResponse unfollow(@RequestBody Relationship relationship) {
        CustomResponse customResponse = new CustomResponse();
        try {
            userService.unfollow(relationship);
            customResponse.setData(true);
            customResponse.setMessage("取消关注成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 查询用户详细信息
    @GetMapping("/info/{userName}")
    @Operation(summary = "获取用户详细信息", description = "传入用户的 userName, 返回详细信息")
    @Parameters(value = {
            @Parameter(name = "userName", description = "用户名", in = ParameterIn.PATH, example = "WFY")
    })
    public CustomResponse info(@PathVariable String userName){
        CustomResponse customResponse = new CustomResponse();
        try {
            User user = userService.getFullInfo(userName);
            customResponse.setData(user);
            customResponse.setMessage("获取成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}

