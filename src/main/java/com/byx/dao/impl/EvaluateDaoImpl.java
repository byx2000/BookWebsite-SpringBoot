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
    public List<Evaluate> query(IQuery query)
    {
        return query("evaluates", query, Evaluate.class);
    }

    @Override
    public void save(Evaluate evaluate)
    {
        save("evaluates", evaluate);
    }

    @Override
    public void delete(IQuery query)
    {
        delete("evaluates", query);
    }

    @Override
    public void updateState(int evaluateId, int state)
    {
        jdbcTemplate.update("UPDATE evaluates SET state = ? WHERE id == ?",
                state, evaluateId);
    }
}
