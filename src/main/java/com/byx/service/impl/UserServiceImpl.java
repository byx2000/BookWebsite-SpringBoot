package com.byx.service.impl;

import com.byx.dao.IUserDao;
import com.byx.domain.User;
import com.byx.exception.BookWebsiteException;
import com.byx.query.IQuery;
import com.byx.query.UserQuery;
import com.byx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务实现类
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService
{
    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<User> query(IQuery query)
    {
        return userDao.query(query);
    }

    @Override
    public int register(User user)
    {
        // 用户名为空
        if (user.getUsername() == null)
            throw new BookWebsiteException("用户名为空");

        // 查询用户是否已存在
        UserQuery query = new UserQuery();
        query.setUsername(user.getUsername());
        if (userDao.count(query) > 0)
            throw new BookWebsiteException("用户已存在：" + user.getUsername());

        // 密码为空
        if (user.getPassword() == null)
            throw new BookWebsiteException("密码为空");

        // 昵称为空
        if (user.getNickname() == null)
            throw new BookWebsiteException("昵称为空");

        // 插入用户
        return userDao.save(user);
    }
}
