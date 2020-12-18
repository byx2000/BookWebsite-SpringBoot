package com.byx.dao.impl;

import com.byx.dao.IUserDao;
import com.byx.domain.User;
import com.byx.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据访问实现类
 */
@Repository
public class UserDaoImpl extends BaseDao implements IUserDao
{
    @Override
    protected String getTableName()
    {
        return "users";
    }

    @Override
    public int count(Query query)
    {
        return super.count(query);
    }

    @Override
    public List<User> query(Query query)
    {
        return query(query, User.class);
    }

    @Override
    public int save(User user)
    {
        return super.save(user);
    }

    @Override
    public void delete(Query query)
    {
        super.delete(query);
    }
}
