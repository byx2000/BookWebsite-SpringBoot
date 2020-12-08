package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.IFavoriteDao;
import com.byx.domain.Book;
import com.byx.domain.Favorite;
import com.byx.domain.PageBean;
import com.byx.query.*;
import com.byx.service.IFavoriteService;
import com.byx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 收藏服务实现类
 */
@Service
@Transactional
public class FavoriteServiceImpl implements IFavoriteService
{
    @Autowired
    private IFavoriteDao favoriteDao;

    @Autowired
    private IBookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public PageBean<List<Object>> queryFavoriteAndBookByPage(int userId, int pageSize, int currentPage)
    {
        // 查询收藏记录
        PageBean<Favorite> favoritePageBean = favoriteDao.queryByPage(new FavoriteQuery(null, userId, false),
                pageSize, currentPage);

        // 查询每条收藏记录对应的电子书信息
        List<List<Object>> result = new ArrayList<>();
        for (Favorite f : favoritePageBean.getData())
        {
            int bookId = f.getBookId();
            BookQuery bookQuery = new BookQuery();
            bookQuery.setBookId(bookId);
            List<Book> books = bookDao.query(bookQuery);
            result.add(Arrays.asList(f, books.get(0)));
        }

        // 构造分页数据
        PageBean<List<Object>> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(favoritePageBean.getTotalPage());
        pageBean.setTotalCount(favoritePageBean.getTotalCount());
        pageBean.setData(result);

        return pageBean;
    }

    @Override
    public boolean isFavorite(int bookId, int userId)
    {
        return favoriteDao.count(new FavoriteQuery(bookId, userId, false)) > 0;
    }

    @Override
    public void add(Favorite favorite)
    {
        // 判断是否已经收藏
        if (isFavorite(favorite.getBookId(), favorite.getUserId())) return;

        // 设置当前时间
        favorite.setTime(DateUtils.now());

        // 保存
        favoriteDao.save(favorite);
    }

    @Override
    public void cancel(int bookId, int userId)
    {
        favoriteDao.delete(new FavoriteQuery(bookId, userId, true));
    }
}
