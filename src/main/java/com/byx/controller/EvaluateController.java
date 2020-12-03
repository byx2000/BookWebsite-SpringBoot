package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.service.IEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点评控制器
 */
@RestController
@RequestMapping("/evaluate")
public class EvaluateController extends BaseController
{
    @Autowired
    private IEvaluateService evaluateService;

    /**
     * 点评
     * <p>cmd参数取值含义：</p>
     * <p>"like": 点赞</p>
     * <p>"dislike": 点踩</p>
     * <p>"cancelLike": 取消点赞</p>
     * <p>"cancelDislike": 取消点踩</p>
     * @param bookId 电子书id
     * @param cmd 操作
     * @return 成功返回{true, msg}，失败返回{false, msg}
     */
    @RequestMapping("")
    public ResultInfo evaluate(Integer bookId, String cmd)
    {
        // 参数校验
        if (bookId == null || cmd == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        switch (cmd)
        {
            case "like":
                evaluateService.like(bookId, user.getId());
                break;
            case "dislike":
                evaluateService.dislike(bookId, user.getId());
                break;
            case "cancelLike":
                evaluateService.cancelLike(bookId, user.getId());
                break;
            case "cancelDislike":
                evaluateService.cancelDislike(bookId, user.getId());
                break;
            default:
                return ResultInfo.fail("参数错误");
        }

        return ResultInfo.success();
    }

    /**
     * 是否已点赞
     * @param bookId 电子书id
     * @return 成功返回{true, msg}，失败返回{false, msg}
     */
    @RequestMapping("/isLike")
    public ResultInfo isLike(Integer bookId)
    {
        // 参数校验
        if (bookId == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        return ResultInfo.success(evaluateService.isLike(bookId, user.getId()));
    }

    /**
     * 是否已点踩
     * @param bookId 电子书id
     * @return 成功返回{true, msg}，失败返回{false, msg}
     */
    @RequestMapping("/isDislike")
    public ResultInfo isDislike(Integer bookId)
    {
        // 参数校验
        if (bookId == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        return ResultInfo.success(evaluateService.isDislike(bookId, user.getId()));
    }
}
