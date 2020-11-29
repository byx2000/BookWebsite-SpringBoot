package com.byx.dao.impl;

import com.byx.dao.ICommentDao;
import com.byx.domain.Comment;
import com.byx.query.CommentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论数据访问实现类
 */
@Repository
public class CommentDaoImpl implements ICommentDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count(CommentQuery query)
    {
        Integer cnt = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM comments " + query.getQueryString(),
                Integer.class,
                query.getParameters().toArray());
        return cnt == null ? 0 : cnt;
    }

    @Override
    public List<Comment> query(CommentQuery query)
    {
        return jdbcTemplate.query("SELECT * FROM comments " + query.getQueryString(),
                new BeanPropertyRowMapper<>(Comment.class),
                query.getParameters().toArray());
    }

    @Override
    public void save(Comment comment)
    {
        jdbcTemplate.update("INSERT INTO comments (bookId, userId, content, time) VALUES (?, ?, ?, ?)",
                comment.getBookId(), comment.getUserId(), comment.getContent(), comment.getTime());
    }
}
