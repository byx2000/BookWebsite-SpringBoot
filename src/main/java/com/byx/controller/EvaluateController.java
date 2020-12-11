package com.byx.controller;

import com.byx.annotation.RequireLogin;
import com.byx.domain.ResultInfo;
import com.byx.service.IEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 点评控制器
 */
@RestController
@RequestMapping("/evaluate")
@Validated
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
    @RequireLogin
    public ResultInfo evaluate(@NotNull Integer bookId,
                               @NotNull String cmd)
    {
        int userId = getCurrentUser().getId();
        switch (cmd)
        {
            case "like":
                evaluateService.like(bookId, userId);
                break;
            case "dislike":
                evaluateService.dislike(bookId, userId);
                break;
            case "cancelLike":
                evaluateService.cancelLike(bookId, userId);
                break;
            case "cancelDislike":
                evaluateService.cancelDislike(bookId, userId);
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
    @RequireLogin
    public ResultInfo isLike(@NotNull Integer bookId)
    {
        return ResultInfo.success(evaluateService.isLike(bookId, getCurrentUser().getId()));
    }

    /**
     * 是否已点踩
     * @param bookId 电子书id
     * @return 成功返回{true, msg}，失败返回{false, msg}
     */
    @RequestMapping("/isDislike")
    @RequireLogin
    public ResultInfo isDislike(@NotNull Integer bookId)
    {
        return ResultInfo.success(evaluateService.isDislike(bookId, getCurrentUser().getId()));
    }
}
