package com.byx.dao.impl;

import com.byx.dao.IChapterDao;
import com.byx.domain.Chapter;
import com.byx.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电子书章节数据访问实现类
 */
@Repository
public class ChapterDaoImpl extends BaseDao implements IChapterDao
{
    @Override
    protected String getTableName()
    {
        return "chapters";
    }

    @Override
    public int count(Query query)
    {
        return super.count(query);
    }

    @Override
    public List<Chapter> query(Query query)
    {
        return query(query, Chapter.class);
    }
}
