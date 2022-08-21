package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.query.BookQueryCondition;
import com.byx.query.Query;
import com.byx.service.IBookService;
import com.byx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 电子书服务实现类
 */
@Service
@Transactional
public class BookServiceImpl implements IBookService {
    @Autowired
    private IBookDao bookDao;

    private Query getQuery(BookQueryCondition bookQueryCondition) {
        Query query = new Query();
        if (bookQueryCondition.getBookId() != null)
            query.addWhere("id == ?", bookQueryCondition.getBookId());
        if (bookQueryCondition.getCategoryId() != null)
            query.addWhere("categoryId == ?", bookQueryCondition.getCategoryId());
        if (bookQueryCondition.getDaysAgo() != null && bookQueryCondition.getDaysAgo() > 0)
            query.addWhere("updateTime >= ?", DateUtils.daysAgo(bookQueryCondition.getDaysAgo()));
        if (bookQueryCondition.getKeyword() != null)
            query.addWhere("name LIKE ? OR author LIKE ? OR description LIKE ?",
                    "%" + bookQueryCondition.getKeyword() + "%", "%" + bookQueryCondition.getKeyword() + "%", "%" + bookQueryCondition.getKeyword() + "%");

        if (bookQueryCondition.getOrderBy() != null) {
            switch (bookQueryCondition.getOrderBy()) {
                case "updateTime": // 按更新时间排序
                    query.addOrder("updateTime", true);
                    break;
                case "wordCount": // 按字数排序
                    query.addOrder("wordCount", true);
                    break;
                case "heat": // 按热度排序
                    query.addOrder("heat", true);
                    break;
                case "score": // 按得分排序
                    query.addOrder("score", true);
                    break;
                case "random": // 随机顺序
                    query.addOrder("RANDOM()");
                    break;
            }
        }

        if (bookQueryCondition.getLimit() != null)
            query.setLimit(bookQueryCondition.getLimit());

        if (bookQueryCondition.getOffset() != null)
            query.setOffset(bookQueryCondition.getOffset());

        return query;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> query(BookQueryCondition bookQueryCondition) {
        return bookDao.query(getQuery(bookQueryCondition));
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<Book> queryByPage(BookQueryCondition bookQueryCondition, int pageSize, int currentPage) {
        return bookDao.queryByPage(getQuery(bookQueryCondition), pageSize, currentPage);
    }

    @Override
    public List<Book> searchPredict(String keyword, int count) {
        List<Book> books = bookDao.query(new Query()
                .addWhere("name LIKE ?", "%" + keyword + "%")
                .addOrder("length(name)")
                .setLimit(count));
        for (Book book : books) {
            book.setDescription(null);
        }
        return books;
    }
}
