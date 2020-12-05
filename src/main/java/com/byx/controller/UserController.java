package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.query.UserQuery;
import com.byx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    /**
     * 注册
     * @param user 新用户
     * @param avatar 头像图片
     * @return 成功返回{true, msg}，失败返回{false, msg}
     */
    @RequestMapping("/register")
    public ResultInfo register(User user, MultipartFile avatar) throws Exception
    {
        // 保存用户数据，并获取id
        int id = userService.register(user);

        // 保存用户头像
        File uploadPath = new File(getStaticResourcePath(), "upload/avatar");
        avatar.transferTo(new File(uploadPath, id + ".jpg"));

        return ResultInfo.success();
    }
}
