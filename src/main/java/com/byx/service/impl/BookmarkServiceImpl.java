package com.byx.service.impl;

import com.byx.dao.IBookmarkDao;
import com.byx.domain.Bookmark;
import com.byx.query.Query;
import com.byx.service.IBookmarkService;
import com.byx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 书签服务实现类
 */
@Service
@Transactional
public class BookmarkServiceImpl implements IBookmarkService
{
    @Autowired
    private IBookmarkDao bookmarkDao;

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
}
