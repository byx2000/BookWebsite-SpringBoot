package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.IChapterDao;
import com.byx.domain.Book;
import com.byx.domain.Chapter;
import com.byx.query.BookQuery;
import com.byx.query.ChapterQuery;
import com.byx.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 电子书章节服务实现类
 */
@Service
public class ChapterServiceImpl implements IChapterService
{
    @Autowired
    private IChapterDao chapterDao;

    @Autowired
    private IBookDao bookDao;

    @Override
    public int getChapterCount(int bookId)
    {
        return chapterDao.count(new ChapterQuery(bookId, null));
    }

    @Override
    public List<Object> getChapterAndBook(int bookId, int chapter)
    {
        // 查询文本信息
        Chapter c = chapterDao.query(new ChapterQuery(bookId, chapter)).get(0);

        // 查询电子书信息
        BookQuery bookQuery = new BookQuery();
        bookQuery.setBookId(bookId);
        Book book = bookDao.query(bookQuery).get(0);

        return Arrays.asList(c, book);
    }
}
