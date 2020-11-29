package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.query.BookQuery;
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
    public List<Book> query(BookQuery query)
    {
        return bookDao.query(query);
    }

    @Override
    public PageBean<Book> queryByPage(BookQuery query, int pageSize, int currentPage)
    {
        // 计算结果总数
        query.setLimit(null);
        query.setOffset(null);
        int totalCount = bookDao.count(query);

        // 获取查询结果
        query.setLimit(pageSize);
        query.setOffset(pageSize * (currentPage - 1));
        List<Book> books = bookDao.query(query);

        // 组装PageBean
        PageBean<Book> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalCount(totalCount);
        int totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(books);

        return pageBean;
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
