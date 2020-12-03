package com.byx.dao;

import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.query.IQuery;
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
    int count(IQuery query);

    /**
     * 查询
     * @param query 查询条件
     * @return 电子书列表
     */
    List<Book> query(IQuery query);

    /**
     * 分页查询
     * @param query 查询条件
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<Book> queryByPage(Query query, int pageSize, int currentPage);

    void increaseLikeCount(int bookId);
    void decreaseLikeCount(int bookId);
    void increaseDislikeCount(int bookId);
    void decreaseDislikeCount(int bookId);
}
