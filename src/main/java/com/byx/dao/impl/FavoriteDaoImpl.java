package com.byx.dao.impl;

import com.byx.dao.IFavoriteDao;
import com.byx.domain.Favorite;
import com.byx.query.IQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏数据访问实现类
 */
@Repository
public class FavoriteDaoImpl extends BaseDao implements IFavoriteDao
{
    @Override
    public int count(IQuery query)
    {
        return super.count(query, "favorites");
    }

    @Override
    public List<Favorite> query(IQuery query)
    {
        return super.query(query, "favorites", Favorite.class);
    }
}
