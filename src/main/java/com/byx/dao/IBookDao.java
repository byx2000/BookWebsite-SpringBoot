package com.byx.dao;

import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.query.Query;

import java.util.List;

/**
 * 电子书数据访问接口
 */
public interface IBookDao
{
    /**
     * 根据条件查询电子书数量
     * @param query 查询条件
     * @return 结果数量
     */
    int count(Query query);

    /**
     * 查询
     * @param query 查询条件
     * @return 电子书列表
     */
    List<Book> query(Query query);

    /**
     * 分页查询
     * @param query 查询条件
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<Book> queryByPage(Query query, int pageSize, int currentPage);

    /**
     * 点赞数量+1
     * @param bookId 电子书id
     */
    void increaseLikeCount(int bookId);

    /**
     * 点赞数量-1
     * @param bookId 电子书id
     */
    void decreaseLikeCount(int bookId);

    /**
     * 点踩数量+1
     * @param bookId 电子书id
     */
    void increaseDislikeCount(int bookId);

    /**
     * 点踩数量-1
     * @param bookId 电子书id
     */
    void decreaseDislikeCount(int bookId);
}
