package com.byx.dao;

import com.byx.domain.Comment;
import com.byx.query.IQuery;

import java.util.List;

/**
 * 评论数据访问接口
 */
public interface ICommentDao
{
    /**
     * 根据条件查询评论数量
     * @param query 查询条件
     * @return 结果总数
     */
    int count(IQuery query);

    /**
     * 根据条件查询评论
     * @param query 查询条件
     * @return 评论列表
     */
    List<Comment> query(IQuery query);

    /**
     * 保存评论
     * @param comment 评论数据
     */
    void save(Comment comment);
}
