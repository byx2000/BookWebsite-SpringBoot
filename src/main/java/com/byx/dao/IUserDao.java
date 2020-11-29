package com.byx.dao;

import com.byx.domain.User;
import com.byx.query.UserQuery;

import java.util.List;

/**
 * 用户数据访问接口
 */
public interface IUserDao
{
    /**
     * 根据条件查询用户
     * @param query 查询条件
     * @return 用户列表
     */
    List<User> query(UserQuery query);
}
