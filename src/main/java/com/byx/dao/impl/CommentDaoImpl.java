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
    public int count(Query query)
    {
        return count("comments", query);
    }

    @Override
    public List<Comment> query(Query query)
    {
        return query("comments", query, Comment.class);
    }

    @Override
    public PageBean<Comment> queryByPage(Query query, int pageSize, int currentPage)
    {
        return queryByPage("comments", query, Comment.class, pageSize, currentPage);
    }

    @Override
    public void save(Comment comment)
    {
        save("comments", comment);
    }

    @Override
    public void delete(Query query)
    {
        delete("comments", query);
    }
}
