package com.byx.service;

import com.byx.domain.User;

/**
 * 用户服务接口
 */
public interface IUserService {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录用户信息
     */
    User login(String username, String password);

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 新用户的id
     */
    int register(String username, String password, String nickname);

    /**
     * 删除用户
     *
     * @param userId 用户id
     */
    void delete(int userId);
}
