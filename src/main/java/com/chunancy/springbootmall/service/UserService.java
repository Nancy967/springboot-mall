package com.chunancy.springbootmall.service;

import com.chunancy.springbootmall.dto.UserRegisterRequest;
import com.chunancy.springbootmall.model.User;

public interface UserService {
    User getUserById(Integer userId);
    Integer creatUser(UserRegisterRequest userRegisterRequest);
}
