package com.byx.service;

import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.query.IQuery;

import java.util.List;

/**
 * 评论服务接口
 */
public interface ICommentService
{
    /**
     * 查找指定电子书的所有评论
     * @param bookId 电子书id
     * @return 评论列表
     */
    List<Comment> queryByBookId(int bookId);

    /**
     * 查找指定用户的所有评论
     * @param userId 用户id
     * @return 评论列表
     */
    PageBean<Comment> queryByUserId(int userId, int pageSize, int currentPage);

    /**
     * 保存评论
     * @param comment 评论数据
     */
    void save(Comment comment);

    /**
     * 删除评论
     * @param query 条件
     */
    void delete(IQuery query);
}
