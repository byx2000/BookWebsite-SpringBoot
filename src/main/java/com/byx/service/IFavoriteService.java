package com.byx.service;

import com.byx.domain.Favorite;
import com.byx.domain.PageBean;
import com.byx.query.IQuery;
import com.byx.query.Query;

import java.util.List;

/**
 * 收藏服务接口
 */
public interface IFavoriteService
{
    /**
     * 分页查询
     * @param query 查询条件
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 结果列表，每个列表项包含收藏数据和与之关联的电子书数据
     */
    PageBean<List<Object>> queryByPage(Query query, int pageSize, int currentPage);

    /**
     * 添加收藏
     * @param favorite 收藏记录
     */
    void add(Favorite favorite);

    /**
     * 取消收藏
     * @param query 收藏记录
     */
    void cancel(IQuery query);
}
