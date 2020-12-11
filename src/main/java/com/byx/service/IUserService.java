package com.byx.service;

import com.byx.domain.User;

/**
 * 用户服务接口
 */
public interface IUserService
{
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 登录用户信息
     */
    User login(String username, String password);

    /**
     * 根据id查询用户
     * @param userId 用户id
     * @return 用户列表
     */
    User queryById(int userId);

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 新用户的id
     */
    int register(String username, String password, String nickname);
}
