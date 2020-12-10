package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.ICommentDao;
import com.byx.dao.IUserDao;
import com.byx.domain.Book;
import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.domain.User;
import com.byx.query.Query;
import com.byx.service.ICommentService;
import com.byx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 评论服务实现类
 */
@Service
@Transactional
public class CommentServiceImpl implements ICommentService
{
    @Autowired
    private ICommentDao commentDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IBookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public PageBean<List<Object>> queryCommentsAndUsersByBookId(int bookId, int pageSize, int currentPage)
    {
        // 查询评论
        PageBean<Comment> commentPageBean = commentDao.queryByPage(new Query().addWhere("bookId == ?", bookId)
                        .addOrder("time", true),
                pageSize, currentPage);

        // 查询评论对应的用户
        List<List<Object>> result = new ArrayList<>();
        for (Comment comment : commentPageBean.getData())
        {
            User user = userDao.query(new Query().addWhere("id == ?", comment.getUserId())).get(0);
            user.setUsername(null);
            user.setPassword(null);
            result.add(Arrays.asList(comment, user));
        }

        // 构造PageBean
        PageBean<List<Object>> pageBean = new PageBean<>();
        pageBean.setTotalCount(commentPageBean.getTotalCount());
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setData(result);

        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<List<Object>> queryCommentsAndBooksByUserId(int userId, int pageSize, int currentPage)
    {
        // 查询评论
        PageBean<Comment> commentPageBean = commentDao.queryByPage(new Query().addWhere("userId == ?", userId)
                        .addOrder("time", true),
                pageSize, currentPage);

        // 查询评论对应的电子书
        List<List<Object>> result = new ArrayList<>();
        for (Comment comment : commentPageBean.getData())
        {
            Book book = bookDao.query(new Query().addWhere("id == ?", comment.getBookId())).get(0);
            result.add(Arrays.asList(comment, book));
        }

        // 构造PageBean
        PageBean<List<Object>> pageBean = new PageBean<>();
        pageBean.setTotalCount(commentPageBean.getTotalCount());
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setData(result);

        return pageBean;
    }

    @Override
    public void publish(Comment comment)
    {
        comment.setTime(DateUtils.now());
        commentDao.save(comment);
    }

    @Override
    public void delete(int commentId)
    {
        commentDao.delete(new Query().addWhere("id == ?", commentId));
    }
}
