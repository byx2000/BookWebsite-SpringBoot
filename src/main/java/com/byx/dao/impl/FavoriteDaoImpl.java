package com.byx.dao.impl;

import com.byx.dao.IFavoriteDao;
import com.byx.domain.Favorite;
import com.byx.domain.PageBean;
import com.byx.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏数据访问实现类
 */
@Repository
public class FavoriteDaoImpl extends BaseDao implements IFavoriteDao {
    @Override
    protected String getTableName() {
        return "favorites";
    }

    @Override
    public int count(Query query) {
        return super.count(query);
    }

    @Override
    public List<Favorite> query(Query query) {
        return query(query, Favorite.class);
    }

    @Override
    public PageBean<Favorite> queryByPage(Query query, int pageSize, int currentPage) {
        return queryByPage(query, Favorite.class, pageSize, currentPage);
    }

    @Override
    public void save(Favorite favorite) {
        super.save(favorite);
    }

    @Override
    public void delete(Query query) {
        super.delete(query);
    }
}
