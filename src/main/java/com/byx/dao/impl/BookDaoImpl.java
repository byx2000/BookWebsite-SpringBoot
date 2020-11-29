package com.byx.dao.impl;

import com.byx.dao.IBookDao;
import com.byx.domain.Book;
import com.byx.query.BookQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电子书数据访问实现类
 */
@Repository
public class BookDaoImpl implements IBookDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count(BookQuery query)
    {
        Integer cnt = jdbcTemplate.queryForObject("SELECT COUNT(*) AS cnt FROM books " + query.getQueryString(),
                Integer.class,
                query.getParameters().toArray());
        return cnt == null ? 0 : cnt;
    }

    @Override
    public List<Book> query(BookQuery query)
    {
        return jdbcTemplate.query("SELECT * FROM books " + query.getQueryString(),
                new BeanPropertyRowMapper<>(Book.class),
                query.getParameters().toArray());
    }

    @Override
    public List<Book> getRandomBooks(int count)
    {
        return jdbcTemplate.query("SELECT * FROM books ORDER BY RANDOM() LIMIT ?",
                new BeanPropertyRowMapper<>(Book.class),
                count);
    }

    @Override
    public List<Book> getSearchSuggestion(String keyword, int count)
    {
        return jdbcTemplate.query("SELECT * FROM (SELECT * FROM books ORDER BY heat DESC LIMIT ?) ORDER BY RANDOM() LIMIT ?",
                new BeanPropertyRowMapper<>(Book.class),
                2 * count, count);
    }

    @Override
    public List<Book> getSimilarRecommend(int categoryId, int count)
    {
        return jdbcTemplate.query("SELECT * FROM books WHERE categoryId == ? ORDER BY score DESC LIMIT ?",
                new BeanPropertyRowMapper<>(Book.class),
                categoryId, count);
    }
}