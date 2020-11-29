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

    /**
     * 获取指定数量的随机电子书
     * @param count 数量
     * @return 电子书列表
     */
    List<Book> getRandomBooks(int count);

    /**
     * 搜索建议
     * @param keyword 搜索关键字
     * @param count 数量
     * @return 搜索建议列表
     */
    List<Book> getSearchSuggestion(String keyword, int count);

    /**
     * 同类推荐
     * @param categoryId 类别id
     * @param count 数量
     * @return 推荐列表
     */
    List<Book> getSimilarRecommend(int categoryId, int count);
}
