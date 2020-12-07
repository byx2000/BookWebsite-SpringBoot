package com.byx.dao;

import com.byx.domain.Chapter;
import com.byx.query.IQuery;

import java.util.List;

/**
 * 电子书章节数据访问接口
 */
public interface IChapterDao
{
    /**
     * 查询结果总数
     * @param query 查询条件
     * @return 结果总数
     */
    int count(IQuery query);

    /**
     * 查询结果列表
     * @param query 查询条件
     * @return 结果列表
     */
    List<Chapter> query(IQuery query);
}
