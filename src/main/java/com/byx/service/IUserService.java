package com.byx.service;

import com.byx.domain.User;
import com.byx.query.IQuery;

import java.util.List;

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
     * 根据条件查询用户
     * @param query 查询条件
     * @return 用户列表
     */
    List<User> query(IQuery query);

    /**
     * 注册
     * @param user 新用户
     */
    void register(User user);
}
