package com.byx.dao;

import com.byx.domain.User;
import com.byx.query.IQuery;

import java.util.List;

/**
 * 用户数据访问接口
 */
public interface IUserDao
{
    /**
     * 查询结果总数
     * @param query 查询条件
     * @return 结果总数
     */
    int count(IQuery query);

    /**
     * 查询
     * @param query 查询条件
     * @return 用户列表
     */
    List<User> query(IQuery query);

    /**
     * 保存用户
     * @param user 用户
     * @return 新用户的id
     */
    int save(User user);
}
