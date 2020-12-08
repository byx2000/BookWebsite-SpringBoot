package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.query.BookQuery;
import com.byx.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 电子书服务实现类
 */
@Service
@Transactional
public class BookServiceImpl implements IBookService
{
    @Autowired
    private IBookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public List<Book> query(BookQuery query)
    {
        return bookDao.query(query);
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<Book> queryByPage(BookQuery query, int pageSize, int currentPage)
    {
        return bookDao.queryByPage(query, pageSize, currentPage);
    }
}
