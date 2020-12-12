package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.IBookmarkDao;
import com.byx.domain.Book;
import com.byx.domain.Bookmark;
import com.byx.domain.PageBean;
import com.byx.query.Query;
import com.byx.service.IBookmarkService;
import com.byx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 书签服务实现类
 */
@Service
@Transactional
public class BookmarkServiceImpl implements IBookmarkService
{
    @Autowired
    private IBookmarkDao bookmarkDao;

    @Autowired
    private IBookDao bookDao;

    @Override
    public void add(int userId, int bookId, int chapter)
    {
        // 防止重复插入
        if (isBookmark(userId, bookId, chapter)) return;

        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userId);
        bookmark.setBookId(bookId);
        bookmark.setChapter(chapter);
        bookmark.setTime(DateUtils.now());
        bookmarkDao.save(bookmark);
    }

    @Override
    public void remove(int userId, int bookId, int chapter)
    {
        bookmarkDao.delete(new Query()
                .addWhere("userId == ?", userId)
                .addWhere("bookId == ?", bookId)
                .addWhere("chapter == ?", chapter));
    }

    @Override
    public boolean isBookmark(int userId, int bookId, int chapter)
    {
        return bookmarkDao.count(new Query()
                .addWhere("userId == ?", userId)
                .addWhere("bookId == ?", bookId)
                .addWhere("chapter == ?", chapter)) > 0;
    }

    @Override
    public PageBean<List<Object>> queryBookmarksAndBooksByUserId(int userId, int pageSize, int currentPage)
    {
        // 获取书签列表
        PageBean<Bookmark> bookmarkPageBean = bookmarkDao.queryByPage(
                new Query().addWhere("userId == ?", userId), pageSize, currentPage);

        // 获取书签对应的电子书信息
        List<List<Object>> result = new ArrayList<>();
        for (Bookmark bookmark : bookmarkPageBean.getData())
        {
            Book book = bookDao.query(new Query().addWhere("id == ?", bookmark.getBookId())).get(0);
            result.add(Arrays.asList(bookmark, book));
        }

        // 构造PageBean
        PageBean<List<Object>> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalCount(bookmarkPageBean.getTotalCount());
        pageBean.setData(result);
        return pageBean;
    }
}
