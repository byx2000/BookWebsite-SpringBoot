package com.byx.dao;

import com.byx.domain.Text;
import com.byx.query.IQuery;

import java.util.List;

/**
 * 电子书文本数据访问接口
 */
public interface ITextDao
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
    List<Text> query(IQuery query);
}
