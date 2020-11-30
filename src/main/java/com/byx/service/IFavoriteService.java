package com.byx.service;

import com.byx.query.FavoriteQuery;

import java.util.List;

/**
 * 收藏服务接口
 */
public interface IFavoriteService
{
    /**
     * 根据条件查询收藏列表（分页）
     * @param query 查询条件
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 结果列表，每个列表项包括收藏数据和电子书数据
     */
    List<List<Object>> getFavoriteListByPage(FavoriteQuery query, int pageSize, int currentPage);
}
