package com.byx.dao;

import com.byx.domain.Evaluate;
import com.byx.query.Query;

import java.util.List;

/**
 * 点评数据访问接口
 */
public interface IEvaluateDao {
    /**
     * 查询点评记录
     *
     * @param query 查询条件
     * @return 结果列表
     */
    List<Evaluate> query(Query query);

    /**
     * 保存点评记录
     *
     * @param evaluate 点评数据
     */
    void save(Evaluate evaluate);

    /**
     * 删除点评记录
     *
     * @param query 查询条件
     */
    void delete(Query query);

    /**
     * 更新点评记录的状态
     *
     * @param evaluateId 点评记录id
     * @param state      状态：0为赞，1为踩
     */
    void updateState(int evaluateId, int state);
}
