package com.byx.dao.impl;

import com.byx.dao.IFavoriteDao;
import com.byx.domain.Favorite;
import com.byx.domain.PageBean;
import com.byx.query.IQuery;
import com.byx.query.Query;
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

    @Override
    public PageBean<Favorite> queryByPage(Query query, int pageSize, int currentPage)
    {
        return super.queryByPage(query, "favorites", Favorite.class, pageSize, currentPage);
    }

    @Override
    public void save(Favorite favorite)
    {
        super.save(favorite, "favorites");
    }

    @Override
    public void delete(IQuery query)
    {
        super.delete(query, "favorites");
    }
}
