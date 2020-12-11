package com.byx.dao;

import com.byx.domain.Bookmark;
import com.byx.domain.PageBean;
import com.byx.query.Query;

/**
 * 书签数据访问接口
 */
public interface IBookmarkDao
{
    /**
     * 分页查询
     * @param query 查询条件
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<Bookmark> queryByPage(Query query, int pageSize, int currentPage);

    /**
     * 保存
     * @param bookmark 书签
     */
    void save(Bookmark bookmark);

    /**
     * 删除
     * @param query 查询条件
     */
    void delete(Query query);
}
