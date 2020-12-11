package com.byx.controller;

import com.byx.annotation.RequireLogin;
import com.byx.domain.ResultInfo;
import com.byx.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/comment")
@Validated
public class CommentController extends BaseController
{
    @Autowired
    private ICommentService commentService;

    /**
     * 查询评论
     * @param bookId 电子书id
     * @param userId 用户id
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 评论列表或分页数据
     */
    @RequestMapping("/query")
    public ResultInfo query(Integer bookId,
                            Integer userId,
                            @NotNull Integer pageSize,
                            @NotNull Integer currentPage)
    {
        // 查询指定电子书的所有评论
        if (bookId != null)
        {
            return ResultInfo.success(commentService.queryCommentsAndUsersByBookId(bookId, pageSize, currentPage));
        }
        // 查询指定用户的所有评论
        else if (userId != null)
        {
            // 登录校验
            if (getCurrentUser() == null) return ResultInfo.fail("当前未登录");
            return ResultInfo.success(commentService.queryCommentsAndBooksByUserId(userId, pageSize, currentPage));
        }
        // 参数错误
        else
        {
            return ResultInfo.fail("参数错误");
        }
    }

    /**
     * 保存评论
     * @param bookId 电子书id
     * @param content 评论内容
     * @return 操作结果
     */
    @RequestMapping("/publish")
    @RequireLogin
    public ResultInfo publish(@NotNull Integer bookId,
                              @NotNull String content)
    {
        commentService.publish(bookId, getCurrentUser().getId(), content);
        return ResultInfo.success();
    }

    /**
     * 删除评论
     * @param commentId 评论id
     * @return 操作结果
     */
    @RequestMapping("/delete")
    @RequireLogin
    public ResultInfo delete(@NotNull Integer commentId)
    {
        commentService.delete(commentId);
        return ResultInfo.success();
    }
}
