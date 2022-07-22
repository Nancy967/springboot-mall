package com.chunancy.springbootmall.service.impl;

import com.chunancy.springbootmall.dao.UserDao;
import com.chunancy.springbootmall.dto.UserRegisterRequest;
import com.chunancy.springbootmall.model.User;
import com.chunancy.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer creatUser(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
