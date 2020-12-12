package com.byx.service.impl;

import com.byx.dao.IBookmarkDao;
import com.byx.domain.Bookmark;
import com.byx.service.IBookmarkService;
import com.byx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 书签服务实现类
 */
@Service
public class BookmarkServiceImpl implements IBookmarkService
{
    @Autowired
    private IBookmarkDao bookmarkDao;

    @Override
    public void addBookmark(int userId, int bookId, int chapter)
    {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userId);
        bookmark.setBookId(bookId);
        bookmark.setChapter(chapter);
        bookmark.setTime(DateUtils.now());
        bookmarkDao.save(bookmark);
    }
}