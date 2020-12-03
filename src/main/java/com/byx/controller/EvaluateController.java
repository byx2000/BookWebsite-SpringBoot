package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.service.IEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点赞控制器
 */
@RestController
@RequestMapping("/evaluate")
public class EvaluateController extends BaseController
{
    @Autowired
    private IEvaluateService likeService;

    @RequestMapping("/like")
    public ResultInfo like(Integer bookId)
    {
        // 参数校验
        if (bookId == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        likeService.like(bookId, user.getId());
        return ResultInfo.success();
    }

    @RequestMapping("/dislike")
    public ResultInfo dislike(Integer bookId)
    {
        // 参数校验
        if (bookId == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        likeService.dislike(bookId, user.getId());
        return ResultInfo.success();
    }

    @RequestMapping("/cancelLike")
    public ResultInfo cancelLike(Integer bookId)
    {
        // 参数校验
        if (bookId == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        likeService.cancelLike(bookId, user.getId());
        return ResultInfo.success();
    }

    @RequestMapping("/cancelDislike")
    public ResultInfo cancelDislike(Integer bookId)
    {
        // 参数校验
        if (bookId == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        likeService.cancelDislike(bookId, user.getId());
        return ResultInfo.success();
    }
}
