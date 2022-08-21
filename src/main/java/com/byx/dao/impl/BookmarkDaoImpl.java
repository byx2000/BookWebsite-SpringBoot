package com.byx.dao.impl;

import com.byx.dao.IBookmarkDao;
import com.byx.domain.Bookmark;
import com.byx.domain.PageBean;
import com.byx.query.Query;
import org.springframework.stereotype.Repository;

/**
 * 书签数据访问实现类
 */
@Repository
public class BookmarkDaoImpl extends BaseDao implements IBookmarkDao {
    @Override
    protected String getTableName() {
        return "bookmarks";
    }

    @Override
    public int count(Query query) {
        return super.count(query);
    }

    @Override
    public PageBean<Bookmark> queryByPage(Query query, int pageSize, int currentPage) {
        return queryByPage(query, Bookmark.class, pageSize, currentPage);
    }

    @Override
    public void save(Bookmark bookmark) {
        super.save(bookmark);
    }

    @Override
    public void delete(Query query) {
        super.delete(query);
    }
}
