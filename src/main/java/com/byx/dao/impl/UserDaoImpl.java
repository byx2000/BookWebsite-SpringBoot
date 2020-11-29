package com.byx.dao.impl;

import com.byx.dao.IUserDao;
import com.byx.domain.User;
import com.byx.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据访问实现类
 */
@Repository
public class UserDaoImpl implements IUserDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> query(UserQuery query)
    {
        return jdbcTemplate.query("SELECT * FROM users " + query.getQueryString(),
                new BeanPropertyRowMapper<>(User.class),
                query.getParameters().toArray());
    }
}
