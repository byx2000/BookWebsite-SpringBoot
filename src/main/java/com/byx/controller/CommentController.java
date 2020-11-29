package com.byx.controller;

import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.query.CommentQuery;
import com.byx.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController
{
    @Autowired
    private ICommentService commentService;

    /**
     * 根据条件查询评论
     * @param commentQuery 查询条件
     * @param pageSize 每页显示的条数
     * @param currentPage 当前页码
     * @return 结果
     */
    @RequestMapping("/query")
    public ResultInfo query(CommentQuery commentQuery, Integer pageSize, Integer currentPage)
    {
        // 不带分页的查询
        if (pageSize == null || currentPage == null)
        {
            List<Comment> comments = commentService.query(commentQuery);
            return ResultInfo.success(comments);
        }
        // 带分页的查询
        else
        {
            PageBean<Comment> pageBean = commentService.queryByPage(commentQuery, pageSize, currentPage);
            return ResultInfo.success(pageBean);
        }
    }

    /**
     * 保存评论
     * @param bookId 电子书id
     * @param content 评论内容
     * @param session 会话
     * @return 结果
     */
    @RequestMapping("/save")
    public ResultInfo save(Integer bookId, String content, HttpSession session)
    {
        // 参数校验
        if (bookId == null || content == null) return ResultInfo.fail("参数错误");

        // 判断是否登录
        User user = (User) session.getAttribute("user");
        if (user == null) return ResultInfo.fail("当前未登录");

        Comment comment = new Comment();
        comment.setBookId(bookId);
        comment.setUserId(user.getId());
        comment.setContent(content);

        commentService.save(comment);
        return ResultInfo.success();
    }
}
