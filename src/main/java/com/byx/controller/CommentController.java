package com.byx.controller;

import com.byx.domain.Comment;
import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
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
    public ResultInfo query(Integer bookId, Integer userId, Integer pageSize, Integer currentPage)
    {
        // 查询指定电子书的所有评论
        if (bookId != null)
        {
            return ResultInfo.success(commentService.queryByBookId(bookId));
        }
        // 查询指定用户的所有评论
        else if (userId != null && pageSize != null && currentPage != null)
        {
            // 当前未登录
            if (getCurrentUser() == null) return ResultInfo.fail("当前未登录");
            return ResultInfo.success(commentService.queryByUserId(userId, pageSize, currentPage));
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
    @RequestMapping("/save")
    public ResultInfo save(Integer bookId, String content)
    {
        // 参数校验
        if (bookId == null || content == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        Comment comment = new Comment();
        comment.setBookId(bookId);
        comment.setUserId(user.getId());
        comment.setContent(content);

        // 保存
        commentService.save(comment);
        return ResultInfo.success();
    }

    /**
     * 删除评论
     * @param commentId 评论id
     * @return 操作结果
     */
    @RequestMapping("/delete")
    public ResultInfo delete(Integer commentId)
    {
        // 参数校验
        if (commentId == null) return ResultInfo.fail("参数错误");

        // 登录校验
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        // 删除评论
        commentService.delete(commentId);

        return ResultInfo.success();
    }
}
