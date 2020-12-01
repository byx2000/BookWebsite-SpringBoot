package com.byx.controller;

import com.byx.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * 所有Controller的基类
 */
public class BaseController
{
    @Autowired
    private HttpSession session;

    /**
     * 获取当前登录用户
     * @return 如果当前未登录，返回null，否则返回当前登录用户
     */
    protected User getCurrentUser()
    {
        return (User) session.getAttribute("user");
    }

    /**
     * 设置当前登录用户
     * @param user 用户
     */
    protected void setCurrentUser(User user)
    {
        session.setAttribute("user", user);
    }

    /**
     * 销毁当前session
     */
    protected void invalidateSession()
    {
        session.invalidate();
    }
}
