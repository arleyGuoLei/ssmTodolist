package com.ncist.dao;

import com.ncist.model.User;

public interface IUserDao {
    User selectByUsername(String username); // 判断是否存在账户
    int insertUser(User user);
    User loginUser(User user);
}
