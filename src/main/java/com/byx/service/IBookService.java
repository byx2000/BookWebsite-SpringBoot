package com.byx.service;

import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.query.BookQueryCondition;

import java.util.List;

/**
 * 电子书服务接口
 */
public interface IBookService {
    /**
     * 根据条件查询电子书
     *
     * @param query 查询条件
     * @return 电子书列表
     */
    List<Book> query(BookQueryCondition query);

    /**
     * 根据条件分页查询电子书
     *
     * @param query       查询条件
     * @param pageSize    每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<Book> queryByPage(BookQueryCondition query, int pageSize, int currentPage);

    /**
     * 搜索预测
     *
     * @param keyword 关键词
     * @param count   数量
     * @return 预测电子书列表
     */
    List<Book> searchPredict(String keyword, int count);
}
