package com.byx.service;

import com.byx.domain.PageBean;

import java.util.List;

/**
 * 评论服务接口
 */
public interface ICommentService
{
    /**
     * 查找电子书的所有评论及其对应的用户信息
     * @param bookId 电子书id
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据，每个列表项格式为[Comment, User]
     */
    PageBean<List<Object>> queryCommentsOfBook(int bookId, int pageSize, int currentPage);

    /**
     * 查找用户的所有评论及其对应的电子书信息
     * @param userId 用户id
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据，每个列表项格式为[Comment, Book]
     */
    PageBean<List<Object>> queryCommentsOfUser(int userId, int pageSize, int currentPage);

    /**
     * 发表评论
     * @param bookId 电子书id
     * @param userId 用户id
     * @param content 评论内容
     */
    void publish(int bookId, int userId, String content);

    /**
     * 根据id删除评论
     * @param commentId 评论id
     */
    void delete(int commentId);
}
