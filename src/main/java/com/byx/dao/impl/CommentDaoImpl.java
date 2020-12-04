package com.byx.dao.impl;

import com.byx.dao.ICommentDao;
import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.query.IQuery;
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
    public int count(IQuery query)
    {
        return super.count("comments", query);
    }

    @Override
    public List<Comment> query(IQuery query)
    {
        return super.query("comments", query, Comment.class);
    }

    @Override
    public PageBean<Comment> queryByPage(Query query, int pageSize, int currentPage)
    {
        return super.queryByPage("comments", query, Comment.class, pageSize, currentPage);
    }

    @Override
    public void save(Comment comment)
    {
        super.save("comments", comment);
    }

    @Override
    public void delete(IQuery query)
    {
        super.delete("comments", query);
    }
}
