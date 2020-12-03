package com.byx.dao.impl;

import com.byx.dao.IEvaluateDao;
import com.byx.domain.Evaluate;
import com.byx.query.IQuery;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * 点评数据访问实现类
 */
@Repository
public class EvaluateDaoImpl extends BaseDao implements IEvaluateDao
{
    private static class EvaluateQuery implements IQuery
    {
        private final int bookId;
        private final int userId;

        private EvaluateQuery(int bookId, int userId)
        {
            this.bookId = bookId;
            this.userId = userId;
        }

        @Override
        public String getQueryString()
        {
            return " WHERE bookId == ? AND userId == ?";
        }

        @Override
        public List<Object> getParameters()
        {
            return Arrays.asList(bookId, userId);
        }
    }

    @Override
    public List<Evaluate> query(int bookId, int userId)
    {
        return query(new EvaluateQuery(bookId, userId), "evaluates", Evaluate.class);
    }

    @Override
    public void save(Evaluate evaluate)
    {
        save(evaluate, "evaluates");
    }

    @Override
    public void delete(int evaluateId)
    {
        deleteById(evaluateId, "evaluates");
    }

    @Override
    public void updateState(int evaluateId, int state)
    {
        jdbcTemplate.update("UPDATE evaluates SET state = ? WHERE id == ?",
                state, evaluateId);
    }
}
