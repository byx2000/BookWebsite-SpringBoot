package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;

@RestController
@RequestMapping("/user")
@Validated
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
    public ResultInfo login(@NotNull String username,
                            @NotNull String password)
    {
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
     * 根据id查询用户
     * @param userId 用户id
     * @return 结果
     */
    @RequestMapping("/query")
    public ResultInfo query(@NotNull Integer userId)
    {
        return ResultInfo.success(userService.queryById(userId));
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
