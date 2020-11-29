package com.byx.service;

import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.query.BookQuery;

import java.util.List;

/**
 * 电子书服务接口
 */
public interface IBookService
{
    /**
     * 根据条件查询电子书（无分页）
     * @param query 查询条件
     * @return 电子书列表
     */
    List<Book> query(BookQuery query);

    /**
     * 根据条件查询电子书（分页）
     * @param query 查询条件
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<Book> queryByPage(BookQuery query, int pageSize, int currentPage);

    /**
     * 随机电子书
     * @param count 数量
     * @return 电子书列表
     */
    List<Book> getRandomBooks(int count);

    /**
     * 搜索建议
     * @param keyword 关键词
     * @param count 数量
     * @return 电子书列表
     */
    List<Book> getSearchSuggestion(String keyword, int count);

    /**
     * 同类推荐
     * @param categoryId 类型id
     * @param count 数量
     * @return 电子书列表
     */
    List<Book> getSimilarRecommend(int categoryId, int count);
}
