package com.byx.service.impl;

import com.byx.dao.IUserDao;
import com.byx.domain.User;
import com.byx.query.UserQuery;
import com.byx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements IUserService
{
    @Autowired
    private IUserDao userDao;

    @Override
    public User login(String username, String password)
    {
        UserQuery query = new UserQuery();
        query.setUsername(username);
        query.setPassword(password);
        List<User> users = userDao.query(query);
        if (users.size() != 1) return null;
        return users.get(0);
    }

    @Override
    public List<User> query(UserQuery query)
    {
        return userDao.query(query);
    }
}
