package com.byx.dao.impl;

import com.byx.dao.IBookDao;
import com.byx.domain.Book;
import com.byx.query.IQuery;
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
        return super.count(query, "books");
    }

    @Override
    public List<Book> query(IQuery query)
    {
        return super.query(query, "books", Book.class);
    }
}