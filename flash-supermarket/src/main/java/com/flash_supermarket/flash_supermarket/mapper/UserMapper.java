package com.flash_supermarket.flash_supermarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flash_supermarket.flash_supermarket.dao.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
