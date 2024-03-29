package com.byx.controller;

import com.byx.domain.User;
import com.byx.exception.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * 所有Controller的基类，包含一些公共方法
 */
public class BaseController {
    @Autowired
    private HttpSession session;

    /**
     * 获取当前登录用户
     *
     * @return 如果当前未登录，返回null，否则返回当前登录用户
     */
    protected User getCurrentUser() {
        return (User) session.getAttribute("user");
    }

    /**
     * 设置当前登录用户
     *
     * @param user 用户
     */
    protected void setCurrentUser(User user) {
        session.setAttribute("user", user);
    }

    /**
     * 销毁当前session
     */
    protected void invalidateSession() {
        session.invalidate();
    }

    /**
     * 获取静态资源实际路径
     *
     * @return 路径
     */
    protected File getStaticResourcePath() {
        return new File(new ApplicationHome().getDir(), "static");
    }

    /**
     * 设置验证码
     *
     * @param code 验证码
     */
    protected void setCheckCode(String code) {
        session.setAttribute("checkCode", code);
    }

    /**
     * 验证验证码
     *
     * @param input 输入
     */
    protected void verifyCheckCode(String input) {
        String answer = (String) session.getAttribute("checkCode");
        System.out.println(answer + " " + input);
        if (answer == null || !answer.equalsIgnoreCase(input)) {
            throw new LogicException("验证码错误");
        } else {
            session.removeAttribute("checkCode");
        }
    }
}
