package com.byx.dao.impl;

import com.byx.dao.IChapterDao;
import com.byx.domain.Chapter;
import com.byx.query.IQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电子书章节数据访问实现类
 */
@Repository
public class ChapterDaoImpl extends BaseDao implements IChapterDao
{
    @Override
    public int count(IQuery query)
    {
        return count("chapters", query);
    }

    @Override
    public List<Chapter> query(IQuery query)
    {
        return query("chapters", query, Chapter.class);
    }
}
