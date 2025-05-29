package com.flash_supermarket.flash_supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.flash_supermarket.flash_supermarket.converter.UserConverter;
import com.flash_supermarket.flash_supermarket.dao.Relationship;
import com.flash_supermarket.flash_supermarket.dao.User;
import com.flash_supermarket.flash_supermarket.dto.FullUserInfoDTO;
import com.flash_supermarket.flash_supermarket.dto.UserDTO;
import com.flash_supermarket.flash_supermarket.mapper.RelationshipMapper;
import com.flash_supermarket.flash_supermarket.mapper.UserMapper;
import com.flash_supermarket.flash_supermarket.service.UserService;
import com.flash_supermarket.flash_supermarket.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private RelationshipMapper relationshipMapper;

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
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BusinessException(400, "用户名不存在或密码错误");
        }
    }

    @Override
    public void edit(User user) throws BusinessException {
        String name = user.getUserName();
        String passWord = user.getPassWord();
        String des = user.getDescription();
        String avatar = user.getAvatar();

        if (passWord == null){
            throw new BusinessException(400, "密码为空");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", name);
        User findUser = userMapper.selectOne(queryWrapper);

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", name);

        if (!passWord.equals(findUser.getPassWord())){
            updateWrapper.set("pass_word", passWord);
        }

        if (des != null){
            updateWrapper.set("description", des);
        }

        if (avatar != null){
            updateWrapper.set("avatar", avatar);
        }

        userMapper.update(null, updateWrapper);
    }

    @Override
    public void follow(Relationship relationship) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", relationship.getFollowName());
        User findUser = userMapper.selectOne(queryWrapper);

        if (findUser == null){
            throw new BusinessException(400, "关注用户不存在");
        }

        relationshipMapper.insert(relationship);
    }

    @Override
    public void unfollow(Relationship relationship) throws BusinessException {
        QueryWrapper<Relationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", relationship.getUserName());
        queryWrapper.eq("follow_name", relationship.getFollowName());
        int res = relationshipMapper.delete(queryWrapper);
        if (res == 0) {
            throw new BusinessException(400, "用户不存在");
        }
    }

    @Override
    public FullUserInfoDTO getFullInfo(String userName) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User findUser = userMapper.selectOne(queryWrapper);

        QueryWrapper<Relationship> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName);
        List<Relationship> list = relationshipMapper.selectList(wrapper);


        List<User> follows = new ArrayList<>();
        for (Relationship rel : list) {
            String followName = rel.getFollowName();
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.eq("user_name", followName);
            User user = userMapper.selectOne(userWrapper);

            if (user != null) {
                follows.add(user);
            }
        }

        QueryWrapper<Relationship> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("follow_name", userName);
        List<Relationship> list2 = relationshipMapper.selectList(wrapper2);

        List<User> fans = new ArrayList<>();
        for (Relationship rel : list2) {
            String fanName = rel.getUserName();
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.eq("user_name", fanName);
            User user = userMapper.selectOne(userWrapper);

            if (user != null) {
                fans.add(user);
            }
        }
        return UserConverter.userConverter(findUser, follows, fans);
    }
}

