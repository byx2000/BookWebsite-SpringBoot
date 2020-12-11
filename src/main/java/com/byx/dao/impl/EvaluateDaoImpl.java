package com.byx.dao.impl;

import com.byx.dao.IEvaluateDao;
import com.byx.domain.Evaluate;
import com.byx.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 点评数据访问实现类
 */
@Repository
public class EvaluateDaoImpl extends BaseDao implements IEvaluateDao
{
    @Override
    protected String getTableName()
    {
        return "evaluates";
    }

    @Override
    public List<Evaluate> query(Query query)
    {
        return query(query, Evaluate.class);
    }

    @Override
    public void save(Evaluate evaluate)
    {
        super.save(evaluate);
    }

    @Override
    public void delete(Query query)
    {
        super.delete(query);
    }

    @Override
    public void updateState(int evaluateId, int state)
    {
        getJdbcTemplate().update("UPDATE evaluates SET state = ? WHERE id == ?",
                state, evaluateId);
    }
}
