package com.byx.dao.impl;

import com.byx.dao.ICommentDao;
import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论数据访问实现类
 */
@Repository
public class CommentDaoImpl extends BaseDao implements ICommentDao
{
    @Override
    protected String getTableName()
    {
        return "comments";
    }

    @Override
    public int count(Query query)
    {
        return super.count(query);
    }

    @Override
    public List<Comment> query(Query query)
    {
        return query(query, Comment.class);
    }

    @Override
    public PageBean<Comment> queryByPage(Query query, int pageSize, int currentPage)
    {
        return queryByPage(query, Comment.class, pageSize, currentPage);
    }

    @Override
    public void save(Comment comment)
    {
        super.save(comment);
    }

    @Override
    public void delete(Query query)
    {
        super.delete(query);
    }
}
