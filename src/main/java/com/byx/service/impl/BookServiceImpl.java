package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.query.BookQuery;
import com.byx.query.IQuery;
import com.byx.query.Query;
import com.byx.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电子书服务实现类
 */
@Service
public class BookServiceImpl implements IBookService
{
    @Autowired
    private IBookDao bookDao;

    @Override
    public List<Book> query(IQuery query)
    {
        return bookDao.query(query);
    }

    @Override
    public PageBean<Book> queryByPage(Query query, int pageSize, int currentPage)
    {
        return bookDao.queryByPage(query, pageSize, currentPage);
    }

    @Override
    public List<Book> getRandomBooks(int count)
    {
        BookQuery query = new BookQuery();
        query.setOrderBy("random");
        query.setLimit(count);
        return bookDao.query(query);
    }

    @Override
    public List<Book> getSearchSuggestion(String keyword, int count)
    {
        BookQuery query = new BookQuery();
        query.setKeyword(keyword);
        query.setOrderBy("heat");
        query.setLimit(count);
        List<Book> books = bookDao.query(query);
        if (books.size() < count)
        {
            books.addAll(getRandomBooks(count - books.size()));
        }
        return books;
    }

    @Override
    public List<Book> getSimilarRecommend(int categoryId, int count)
    {
        BookQuery query = new BookQuery();
        query.setCategoryId(categoryId);
        query.setOrderBy("score");
        query.setLimit(count);
        return bookDao.query(query);
    }
}
