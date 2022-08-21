package com.byx.dao;

import com.byx.domain.User;
import com.byx.query.Query;

import java.util.List;

/**
 * 用户数据访问接口
 */
public interface IUserDao {
    /**
     * 查询结果总数
     *
     * @param query 查询条件
     * @return 结果总数
     */
    int count(Query query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 用户列表
     */
    List<User> query(Query query);

    /**
     * 保存用户
     *
     * @param user 用户
     * @return 新用户的id
     */
    int save(User user);

    /**
     * 删除用户
     *
     * @param query 查询条件
     */
    void delete(Query query);
}
