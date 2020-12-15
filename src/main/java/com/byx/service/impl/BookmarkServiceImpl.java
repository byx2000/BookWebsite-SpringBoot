package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.IBookmarkDao;
import com.byx.dao.IChapterDao;
import com.byx.domain.Book;
import com.byx.domain.Bookmark;
import com.byx.domain.Chapter;
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

    @Autowired
    private IChapterDao chapterDao;

    @Override
    public void add(int userId, int chapterId)
    {
        // 防止重复插入
        if (isBookmark(userId, chapterId)) return;

        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userId);
        bookmark.setChapterId(chapterId);
        bookmark.setTime(DateUtils.now());
        bookmarkDao.save(bookmark);
    }

    @Override
    public void remove(int userId, int chapterId)
    {
        bookmarkDao.delete(new Query()
                .addWhere("userId == ?", userId)
                .addWhere("chapterId == ?", chapterId));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isBookmark(int userId, int chapterId)
    {
        return bookmarkDao.count(new Query()
                .addWhere("userId == ?", userId)
                .addWhere("chapterId == ?", chapterId)) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<List<Object>> queryBookmarksOfUser(int userId, int pageSize, int currentPage)
    {
        // 获取书签列表
        PageBean<Bookmark> bookmarkPageBean = bookmarkDao.queryByPage(
                new Query().addWhere("userId == ?", userId).addOrder("time", true),
                pageSize, currentPage);

        // 获取书签对应的电子书信息和章节信息
        List<List<Object>> result = new ArrayList<>();
        for (Bookmark bookmark : bookmarkPageBean.getData())
        {
            Chapter chapter = chapterDao.query(new Query().addWhere("id == ?", bookmark.getChapterId())).get(0);
            Book book = bookDao.query(new Query().addWhere("id == ?", chapter.getBookId())).get(0);
            if (chapter.getContent().length() > 200)
                chapter.setContent(chapter.getContent().substring(0, 200));
            chapter.setContent(chapter.getContent().replace("　", ""));
            chapter.setContent(chapter.getContent().replace("\n", ""));
            result.add(Arrays.asList(bookmark, book, chapter));
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
