package com.byx.dao;

import com.byx.domain.Favorite;
import com.byx.query.IQuery;

import java.util.List;

/**
 * 收藏数据访问接口
 */
public interface IFavoriteDao
{
    /**
     * 根据条件查询收藏数量
     * @param query 查询条件
     * @return 查询结果数
     */
    int count(IQuery query);

    /**
     * 根据条件查询收藏列表
     * @param query 查询条件
     * @return 收藏列表
     */
    List<Favorite> query(IQuery query);
}
