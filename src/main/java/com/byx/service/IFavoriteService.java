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
    List<Favorite> query(IQuery query);

    PageBean<List<Object>> queryByPage(Query query, int pageSize, int currentPage);
}
