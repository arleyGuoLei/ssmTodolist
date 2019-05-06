package com.ncist.service.impl;

import com.ncist.dao.IUserDao;
import com.ncist.model.User;
import com.ncist.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    public User login(User user) {
        return userDao.loginUser(user);
    }

}
