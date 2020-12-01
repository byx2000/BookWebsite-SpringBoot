package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.IFavoriteDao;
import com.byx.domain.Book;
import com.byx.domain.Favorite;
import com.byx.domain.PageBean;
import com.byx.query.BookQuery;
import com.byx.query.IQuery;
import com.byx.query.Query;
import com.byx.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FavoriteServiceImpl implements IFavoriteService
{
    @Autowired
    private IFavoriteDao favoriteDao;

    @Autowired
    private IBookDao bookDao;

    @Override
    public List<Favorite> query(IQuery query)
    {
        return favoriteDao.query(query);
    }

    @Override
    public PageBean<List<Object>> queryByPage(Query query, int pageSize, int currentPage)
    {
        PageBean<Favorite> favoritePageBean = favoriteDao.queryByPage(query, pageSize, currentPage);

        List<List<Object>> result = new ArrayList<>();
        for (Favorite f : favoritePageBean.getData())
        {
            int bookId = f.getBookId();
            BookQuery bookQuery = new BookQuery();
            bookQuery.setBookId(bookId);
            List<Book> books = bookDao.query(bookQuery);
            result.add(Arrays.asList(f, books.get(0)));
        }

        PageBean<List<Object>> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(favoritePageBean.getTotalPage());
        pageBean.setTotalCount(favoritePageBean.getTotalCount());
        pageBean.setData(result);

        return pageBean;
    }
}