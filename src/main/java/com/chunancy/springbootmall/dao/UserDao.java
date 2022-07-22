package com.chunancy.springbootmall.dao;

import com.chunancy.springbootmall.dto.UserRegisterRequest;
import com.chunancy.springbootmall.model.User;

public interface UserDao {
    User getUserById(Integer userId);
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
