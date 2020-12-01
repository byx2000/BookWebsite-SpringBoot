package com.byx.dao.impl;

import com.byx.dao.ICommentDao;
import com.byx.domain.Comment;
import com.byx.query.IQuery;
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
        return super.count(query, "comments");
    }

    @Override
    public List<Comment> query(IQuery query)
    {
        return super.query(query, "comments", Comment.class);
    }

    @Override
    public void save(Comment comment)
    {
        jdbcTemplate.update("INSERT INTO comments (bookId, userId, content, time) VALUES (?, ?, ?, ?)",
                comment.getBookId(), comment.getUserId(), comment.getContent(), comment.getTime());
    }
}
