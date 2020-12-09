package com.byx.service;

import com.byx.domain.Comment;
import com.byx.domain.PageBean;

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
     * 发表评论
     * @param comment 评论数据
     */
    void publish(Comment comment);

    /**
     * 根据id删除评论
     * @param commentId 评论id
     */
    void delete(int commentId);
}
