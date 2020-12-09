package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.IChapterDao;
import com.byx.domain.Book;
import com.byx.domain.Chapter;
import com.byx.query.Query;
import com.byx.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 电子书章节服务实现类
 */
@Service
@Transactional
public class ChapterServiceImpl implements IChapterService
{
    @Autowired
    private IChapterDao chapterDao;

    @Autowired
    private IBookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public int getChapterCount(int bookId)
    {
        return chapterDao.count(new Query().addWhere("bookId == ?", bookId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object> getChapterAndBook(int bookId, int chapter)
    {
        // 查询文本信息
        Chapter c = chapterDao.query(new Query().addWhere("bookId == ?", bookId)
                .addWhere("chapter == ?", chapter)).get(0);

        // 查询电子书信息
        Book book = bookDao.query(new Query().addWhere("id == ?", bookId)).get(0);

        return Arrays.asList(c, book);
    }
}
