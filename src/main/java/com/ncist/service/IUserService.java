package com.ncist.service;

import com.ncist.model.User;

public interface IUserService {
    /**
     * 为了判断账号是否已经存在
     * @param username
     * @return
     */
    public User selectByUsername(String username);

    /**
     * 注册 - 1
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);
}
