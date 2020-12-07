package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.ITextDao;
import com.byx.domain.Book;
import com.byx.domain.Text;
import com.byx.query.BookQuery;
import com.byx.query.TextQuery;
import com.byx.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 电子书文本服务实现类
 */
@Service
public class TextServiceImpl implements ITextService
{
    @Autowired
    private ITextDao textDao;

    @Autowired
    private IBookDao bookDao;

    @Override
    public int getChapterCount(int bookId)
    {
        TextQuery query = new TextQuery();
        query.setBookId(bookId);
        return textDao.count(query);
    }

    @Override
    public List<Object> getChapter(int bookId, int chapter)
    {
        // 查询文本信息
        TextQuery textQuery = new TextQuery();
        textQuery.setBookId(bookId);
        textQuery.setChapter(chapter);
        Text text = textDao.query(textQuery).get(0);

        // 查询电子书信息
        BookQuery bookQuery = new BookQuery();
        bookQuery.setBookId(bookId);
        Book book = bookDao.query(bookQuery).get(0);

        return Arrays.asList(text, book);
    }
}
