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
     * 查询指定电子书的所有评论及其对应的用户信息
     * @param bookId 电子书id
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    @RequestMapping("query_of_book")
    public ResultInfo query(@NotNull Integer bookId,
                            @NotNull Integer pageSize,
                            @NotNull Integer currentPage)
    {
        return ResultInfo.success(commentService.queryCommentsOfBook(bookId, pageSize, currentPage));
    }

    /**
     * 查询当前用户的所有评论及其对应的电子书信息
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @param bookName 书名搜索关键词
     * @param isDesc 是否按照时间降序排序
     * @param commentContent 评论内容搜索关键词
     * @return 分页数据
     */
    @RequestMapping("query_of_user")
    @RequireLogin
    public ResultInfo query(@NotNull Integer pageSize,
                            @NotNull Integer currentPage,
                            String bookName,
                            String commentContent,
                            Boolean isDesc)
    {
        if (bookName == null) bookName = "";
        if (commentContent == null) commentContent = "";
        if (isDesc == null) isDesc = true;
        return ResultInfo.success(commentService.queryCommentsOfUser(
                getCurrentUser().getId(), bookName, commentContent, isDesc, pageSize, currentPage));
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
