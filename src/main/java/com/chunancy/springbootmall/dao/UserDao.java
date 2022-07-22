package com.chunancy.springbootmall.dao;

import com.chunancy.springbootmall.dto.UserRegisterRequest;
import com.chunancy.springbootmall.model.User;

public interface UserDao {
    User getUserById(Integer userId);

    User getUserByEmail(String email);
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
