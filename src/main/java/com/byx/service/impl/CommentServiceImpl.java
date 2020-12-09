package com.byx.service.impl;

import com.byx.dao.ICommentDao;
import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.query.Query;
import com.byx.service.ICommentService;
import com.byx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(readOnly = true)
    public List<Comment> queryByBookId(int bookId)
    {
        return commentDao.query(new Query()
                .addWhere("bookId == ?", bookId)
                .addOrder("time", true));
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<Comment> queryByUserId(int userId, int pageSize, int currentPage)
    {
        return commentDao.queryByPage(new Query().addWhere("userId == ?", userId)
                .addOrder("time", true),
                pageSize, currentPage);
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
