package com.byx.dao;

import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.query.Query;

import java.util.List;

/**
 * 评论数据访问接口
 */
public interface ICommentDao {
    /**
     * 根据条件查询评论数量
     *
     * @param query 查询条件
     * @return 结果总数
     */
    int count(Query query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 评论列表
     */
    List<Comment> query(Query query);

    /**
     * 分页查询
     *
     * @param query       查询条件
     * @param pageSize    每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<Comment> queryByPage(Query query, int pageSize, int currentPage);

    /**
     * 保存评论
     *
     * @param comment 评论数据
     */
    void save(Comment comment);

    /**
     * 删除评论
     *
     * @param query 条件
     */
    void delete(Query query);
}
