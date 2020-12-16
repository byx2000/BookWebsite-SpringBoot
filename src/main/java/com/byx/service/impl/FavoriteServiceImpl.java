package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.IFavoriteDao;
import com.byx.domain.Book;
import com.byx.domain.Favorite;
import com.byx.domain.PageBean;
import com.byx.query.Query;
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
    public PageBean<List<Object>> queryFavoritesOfUser(int userId, String bookName, String author, boolean isDesc, int pageSize, int currentPage)
    {
        // 查询收藏记录
        Query favoriteQuery = new Query()
                .addWhere("userId == ?", userId)
                .addWhere("(SELECT name FROM books WHERE books.id == favorites.bookId) LIKE ?", "%" + bookName + "%")
                .addWhere("(SELECT author FROM books WHERE books.id == favorites.bookId) LIKE ?", "%" + author + "%")
                .addOrder("time", isDesc);
        PageBean<Favorite> favoritePageBean = favoriteDao.queryByPage(favoriteQuery, pageSize, currentPage);

        // 查询每条收藏记录对应的电子书信息
        List<List<Object>> result = new ArrayList<>();
        for (Favorite f : favoritePageBean.getData())
        {
            int bookId = f.getBookId();
            List<Book> books = bookDao.query(new Query().addWhere("id == ?", bookId));
            result.add(Arrays.asList(f, books.get(0)));
        }

        // 构造分页数据
        PageBean<List<Object>> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalCount(favoritePageBean.getTotalCount());
        pageBean.setData(result);

        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isFavorite(int bookId, int userId)
    {
        return favoriteDao.count(new Query()
                .addWhere("bookId == ?", bookId)
                .addWhere("userId == ?", userId)) > 0;
    }

    @Override
    public void add(int bookId, int userId)
    {
        // 判断是否已经收藏
        if (isFavorite(bookId, userId)) return;

        // 设置Favorite
        Favorite favorite = new Favorite();
        favorite.setBookId(bookId);
        favorite.setUserId(userId);
        favorite.setTime(DateUtils.now());

        // 保存
        favoriteDao.save(favorite);
    }

    @Override
    public void cancel(int bookId, int userId)
    {
        favoriteDao.delete(new Query().addWhere("bookId == ?", bookId)
                .addWhere("userId == ?", userId));
    }
}
