package com.byx.dao;

import com.byx.domain.Evaluate;
import com.byx.query.IQuery;

import java.util.List;

/**
 * 点评数据访问接口
 */
public interface IEvaluateDao
{
    int count(IQuery query);
    List<Evaluate> query(IQuery query);
    void save(Evaluate evaluate);
    void delete(IQuery query);
    void update(Evaluate evaluate);
}
