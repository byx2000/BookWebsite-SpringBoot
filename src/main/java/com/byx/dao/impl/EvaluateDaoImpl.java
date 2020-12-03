package com.byx.dao.impl;

import com.byx.dao.IEvaluateDao;
import com.byx.domain.Evaluate;
import com.byx.query.IQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 点评数据访问实现类
 */
@Repository
public class EvaluateDaoImpl extends BaseDao implements IEvaluateDao
{
    @Override
    public int count(IQuery query)
    {
        return count(query, "evaluates");
    }

    @Override
    public List<Evaluate> query(IQuery query)
    {
        return query(query, "evaluates", Evaluate.class);
    }

    @Override
    public void save(Evaluate evaluate)
    {
        save(evaluate, "evaluates");
    }

    @Override
    public void delete(IQuery query)
    {
        delete(query, "evaluates");
    }

    @Override
    public void update(Evaluate evaluate)
    {
        jdbcTemplate.update("UPDATE evaluates SET userId = ?, bookId = ?, state = ? WHERE id == ?",
                evaluate.getUserId(), evaluate.getBookId(), evaluate.getState(), evaluate.getId());
    }
}
