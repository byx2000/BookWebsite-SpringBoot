package com.byx.dao;

import com.byx.domain.Book;
import com.byx.query.BookQuery;

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
    int count(BookQuery query);

    /**
     * 根据条件查询电子书
     * @param query 查询条件
     * @return 电子书列表
     */
    List<Book> query(BookQuery query);
}
