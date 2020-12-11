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
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @param avatar 头像
     * @return 操作结果
     * @throws Exception 保存头像时可能抛出IO异常
     */
    @RequestMapping("/register")
    public ResultInfo register(@NotNull String username,
                               @NotNull String password,
                               @NotNull String nickname,
                               @NotNull MultipartFile avatar) throws Exception
    {
        // 保存用户数据，并获取id
        int id = userService.register(username, password, nickname);

        // 保存用户头像
        File uploadPath = new File(getStaticResourcePath(), "upload/avatar");
        avatar.transferTo(new File(uploadPath, id + ".jpg"));

        return ResultInfo.success();
    }
}
