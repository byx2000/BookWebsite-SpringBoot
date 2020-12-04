package com.byx.dao.impl;

import com.byx.dao.IBookDao;
import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.query.IQuery;
import com.byx.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电子书数据访问实现类
 */
@Repository
public class BookDaoImpl extends BaseDao implements IBookDao
{
    @Override
    public int count(IQuery query)
    {
        return count("books", query);
    }

    @Override
    public List<Book> query(IQuery query)
    {
        return query("books", query, Book.class);
    }

    @Override
    public PageBean<Book> queryByPage(Query query, int pageSize, int currentPage)
    {
        return queryByPage("books", query, Book.class, pageSize, currentPage);
    }

    @Override
    public void increaseLikeCount(int bookId)
    {
        jdbcTemplate.update("UPDATE books SET likeCount = likeCount + 1 WHERE id == ?", bookId);
    }

    @Override
    public void decreaseLikeCount(int bookId)
    {
        jdbcTemplate.update("UPDATE books SET likeCount = likeCount - 1 WHERE id == ?", bookId);
    }

    @Override
    public void increaseDislikeCount(int bookId)
    {
        jdbcTemplate.update("UPDATE books SET dislikeCount = dislikeCount + 1 WHERE id == ?", bookId);
    }

    @Override
    public void decreaseDislikeCount(int bookId)
    {
        jdbcTemplate.update("UPDATE books SET dislikeCount = dislikeCount - 1 WHERE id == ?", bookId);
    }
}