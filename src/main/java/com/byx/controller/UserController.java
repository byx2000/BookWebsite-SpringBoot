package com.byx.controller;

import com.byx.annotation.RequireLogin;
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
     * @return 操作结果：成功或失败
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
     * @return 操作结果：成功或失败
     */
    @RequestMapping("/logout")
    @RequireLogin
    public ResultInfo logout()
    {
        invalidateSession();
        return ResultInfo.success();
    }

    /**
     * 获取当前登录用户
     * @return 当前登录用户信息
     */
    @RequestMapping("/current")
    @RequireLogin
    public ResultInfo current()
    {
        return ResultInfo.success(getCurrentUser());
    }

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @param avatar 头像
     * @param checkCode 验证码
     * @return 操作结果：成功或失败
     */
    @RequestMapping("/register")
    public ResultInfo register(@NotNull String username,
                               @NotNull String password,
                               @NotNull String nickname,
                               @NotNull MultipartFile avatar,
                               @NotNull String checkCode)
    {
        // 验证验证码
        verifyCheckCode(checkCode);

        // 保存用户数据，并获取id
        int id = userService.register(username, password, nickname);

        // 保存用户头像
        try
        {
            File uploadPath = new File(getStaticResourcePath(), "upload/avatar");
            avatar.transferTo(new File(uploadPath, id + ".jpg"));
        }
        // 保存失败，删除刚刚添加的用户
        catch (Exception e)
        {
            e.printStackTrace();
            userService.delete(id);
            return ResultInfo.fail("注册失败");
        }

        return ResultInfo.success();
    }
}
