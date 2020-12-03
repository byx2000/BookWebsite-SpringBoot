package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.IEvaluateDao;
import com.byx.domain.Evaluate;
import com.byx.query.EvaluateQuery;
import com.byx.service.IEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 点赞服务实现类
 */
@Service
public class EvaluateServiceImpl implements IEvaluateService
{
    @Autowired
    private IEvaluateDao likeDao;

    @Autowired
    private IBookDao bookDao;

    @Override
    public void like(int bookId, int userId)
    {
        EvaluateQuery query = new EvaluateQuery();
        query.setBookId(bookId);
        query.setUserId(userId);

        // 用户已经进行了点赞或点踩操作
        if (likeDao.count(query) > 0)
        {
            // 获取点赞记录
            Evaluate evaluate = likeDao.query(query).get(0);
            // 如果点赞状态为踩，则更新记录
            if (evaluate.getState() == 1)
            {
                evaluate.setState(0);
                likeDao.update(evaluate);
            }
        }
        // 用户还没有进行点赞或点踩操作
        else
        {
            // 插入记录
            Evaluate evaluate = new Evaluate();
            evaluate.setBookId(bookId);
            evaluate.setUserId(userId);
            evaluate.setState(0);
            likeDao.save(evaluate);
        }
    }

    @Override
    public void cancelLike(int bookId, int userId)
    {
        EvaluateQuery query = new EvaluateQuery();
        query.setBookId(bookId);
        query.setUserId(userId);

        // 用户已经进行了点赞或点踩操作
        if (likeDao.count(query) > 0)
        {
            // 获取点赞记录
            Evaluate evaluate = likeDao.query(query).get(0);
            // 如果点赞状态为赞，则删除记录
            if (evaluate.getState() == 0)
            {
                likeDao.delete(query);
            }
        }
    }

    @Override
    public void dislike(int bookId, int userId)
    {
        EvaluateQuery query = new EvaluateQuery();
        query.setBookId(bookId);
        query.setUserId(userId);

        // 用户已经进行了点赞或点踩操作
        if (likeDao.count(query) > 0)
        {
            // 获取点赞记录
            Evaluate evaluate = likeDao.query(query).get(0);
            // 如果点赞状态为赞，则更新记录
            if (evaluate.getState() == 0)
            {
                evaluate.setState(1);
                likeDao.update(evaluate);
            }
        }
        // 用户还没有进行点赞或点踩操作
        else
        {
            // 插入记录
            Evaluate evaluate = new Evaluate();
            evaluate.setBookId(bookId);
            evaluate.setUserId(userId);
            evaluate.setState(1);
            likeDao.save(evaluate);
        }
    }

    @Override
    public void cancelDislike(int bookId, int userId)
    {
        EvaluateQuery query = new EvaluateQuery();
        query.setBookId(bookId);
        query.setUserId(userId);

        // 用户已经进行了点赞或点踩操作
        if (likeDao.count(query) > 0)
        {
            // 获取点赞记录
            Evaluate evaluate = likeDao.query(query).get(0);
            // 如果点赞状态为踩，则删除记录
            if (evaluate.getState() == 1)
            {
                likeDao.delete(query);
            }
        }
    }
}
