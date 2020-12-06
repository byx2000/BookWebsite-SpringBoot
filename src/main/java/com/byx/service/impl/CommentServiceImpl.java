package com.byx.service.impl;

import com.byx.dao.ICommentDao;
import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.query.IQuery;
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
    public List<Comment> query(IQuery query)
    {
        return commentDao.query(query);
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<Comment> queryByPage(Query query, int pageSize, int currentPage)
    {
        return commentDao.queryByPage(query, pageSize, currentPage);
    }

    @Override
    public void save(Comment comment)
    {
        // 设置评论时间为当前时间
        comment.setTime(DateUtils.now());

        // 保存
        commentDao.save(comment);
    }

    @Override
    public void delete(IQuery query)
    {
        commentDao.delete(query);
    }
}
