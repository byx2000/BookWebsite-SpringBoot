package com.byx.service.impl;

import com.byx.dao.IBookDao;
import com.byx.dao.IEvaluateDao;
import com.byx.domain.Evaluate;
import com.byx.query.Query;
import com.byx.service.IEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 点评服务实现类
 */
@Service
@Transactional
public class EvaluateServiceImpl implements IEvaluateService {
    @Autowired
    private IEvaluateDao evaluateDao;

    @Autowired
    private IBookDao bookDao;

    @Override
    public void like(int bookId, int userId) {
        List<Evaluate> evaluates = evaluateDao.query(new Query()
                .addWhere("bookId == ?", bookId)
                .addWhere("userId == ?", userId));

        // 用户已有点评记录
        if (evaluates.size() > 0) {
            // 获取点评记录
            Evaluate evaluate = evaluates.get(0);
            // 当前点评状态为踩
            if (evaluate.getState() == 1) {
                // 设置当前点评状态为赞
                evaluateDao.updateState(evaluate.getId(), 0);
                // 踩的数量-1
                bookDao.decreaseDislikeCount(bookId);
                // 赞的数量+1
                bookDao.increaseLikeCount(bookId);
            }
        }
        // 用户没有点评记录
        else {
            // 插入点评记录
            Evaluate evaluate = new Evaluate();
            evaluate.setBookId(bookId);
            evaluate.setUserId(userId);
            evaluate.setState(0);
            evaluateDao.save(evaluate);
            // 赞的数量+1
            bookDao.increaseLikeCount(bookId);
        }
    }

    @Override
    public void cancelLike(int bookId, int userId) {
        List<Evaluate> evaluates = evaluateDao.query(new Query()
                .addWhere("bookId == ?", bookId)
                .addWhere("userId == ?", userId));

        // 用户已有点评记录
        if (evaluates.size() > 0) {
            // 获取点评记录
            Evaluate evaluate = evaluates.get(0);
            // 当前点评状态为赞
            if (evaluate.getState() == 0) {
                // 删除点评记录
                evaluateDao.delete(new Query().addWhere("id == ?", evaluate.getId()));
                // 赞的数量-1
                bookDao.decreaseLikeCount(bookId);
            }
        }
    }

    @Override
    public void dislike(int bookId, int userId) {
        List<Evaluate> evaluates = evaluateDao.query(new Query()
                .addWhere("bookId == ?", bookId)
                .addWhere("userId == ?", userId));

        // 用户已有点评记录
        if (evaluates.size() > 0) {
            // 获取点评记录
            Evaluate evaluate = evaluates.get(0);
            // 当前点评状态为赞
            if (evaluate.getState() == 0) {
                // 设置当前点评状态为踩
                evaluateDao.updateState(evaluate.getId(), 1);
                // 赞的数量-1
                bookDao.decreaseLikeCount(bookId);
                // 踩的数量+1
                bookDao.increaseDislikeCount(bookId);
            }
        }
        // 用户没有点评记录
        else {
            // 插入点评记录
            Evaluate evaluate = new Evaluate();
            evaluate.setBookId(bookId);
            evaluate.setUserId(userId);
            evaluate.setState(1);
            evaluateDao.save(evaluate);
            // 踩的数量+1
            bookDao.increaseDislikeCount(bookId);
        }
    }

    @Override
    public void cancelDislike(int bookId, int userId) {
        List<Evaluate> evaluates = evaluateDao.query(new Query()
                .addWhere("bookId == ?", bookId)
                .addWhere("userId == ?", userId));

        // 用户已有点评记录
        if (evaluates.size() > 0) {
            // 获取点评记录
            Evaluate evaluate = evaluates.get(0);
            // 当前点评记录为踩
            if (evaluate.getState() == 1) {
                // 删除点评记录
                evaluateDao.delete(new Query().addWhere("id == ?", evaluate.getId()));
                // 踩的数量-1
                bookDao.decreaseDislikeCount(bookId);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isLike(int bookId, int userId) {
        List<Evaluate> evaluates = evaluateDao.query(new Query()
                .addWhere("bookId == ?", bookId)
                .addWhere("userId == ?", userId));
        if (evaluates.isEmpty()) return false;
        return evaluates.get(0).getState() == 0;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isDislike(int bookId, int userId) {
        List<Evaluate> evaluates = evaluateDao.query(new Query()
                .addWhere("bookId == ?", bookId)
                .addWhere("userId == ?", userId));
        if (evaluates.isEmpty()) return false;
        return evaluates.get(0).getState() == 1;
    }
}
