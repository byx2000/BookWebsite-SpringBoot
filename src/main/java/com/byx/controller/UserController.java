package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.query.UserQuery;
import com.byx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController
{
    @Autowired
    private IUserService userService;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    @RequestMapping("/login")
    public ResultInfo login(String username, String password)
    {
        // 参数校验
        if (username == null || password == null) return ResultInfo.fail("参数错误");

        User user = userService.login(username, password);
        if (user == null) return ResultInfo.fail("用户名或密码错误");

        // 设置当前登录用户
        setCurrentUser(user);
        return ResultInfo.success(user);
    }

    /**
     * 注销
     * @return 结果
     */
    @RequestMapping("/logout")
    public ResultInfo logout()
    {
        invalidateSession();
        return ResultInfo.success();
    }

    /**
     * 获取当前登录用户
     * @return 结果
     */
    @RequestMapping("/current")
    public ResultInfo current()
    {
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");
        return ResultInfo.success(user);
    }

    /**
     * 查询用户
     * @param userQuery 查询条件
     * @return 结果
     */
    @RequestMapping("/query")
    public ResultInfo query(UserQuery userQuery)
    {
        userQuery.setPassword(null);
        List<User> users = userService.query(userQuery);
        for (User u : users)
        {
            u.setPassword(null);
        }
        return ResultInfo.success(users);
    }
}
