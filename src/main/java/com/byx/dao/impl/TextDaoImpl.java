package com.byx.dao.impl;

import com.byx.dao.ITextDao;
import com.byx.domain.Text;
import com.byx.query.IQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电子书文本数据访问实现类
 */
@Repository
public class TextDaoImpl extends BaseDao implements ITextDao
{
    @Override
    public int count(IQuery query)
    {
        return count("texts", query);
    }

    @Override
    public List<Text> query(IQuery query)
    {
        return query("texts", query, Text.class);
    }
}
