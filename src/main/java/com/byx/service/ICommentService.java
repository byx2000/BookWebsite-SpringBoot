package com.byx.service;

import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.query.IQuery;
import com.byx.query.Query;

import java.util.List;

/**
 * 评论服务接口
 */
public interface ICommentService
{
    /**
     * 根据条件查询评论（无分页）
     * @param query 查询条件
     * @return 评论列表
     */
    List<Comment> query(IQuery query);

    /**
     * 根据条件查询评论（无分页）
     * @param query 查询条件
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<Comment> queryByPage(Query query, int pageSize, int currentPage);

    /**
     * 保存评论
     * @param comment 评论数据
     */
    void save(Comment comment);
}
