package com.byx.dao.impl;

import com.byx.dao.IFavoriteDao;
import com.byx.domain.Favorite;
import com.byx.query.FavoriteQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏数据访问实现类
 */
@Repository
public class FavoriteDaoImpl implements IFavoriteDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count(FavoriteQuery query)
    {
        Integer cnt = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM favorites " + query.getQueryString(),
                Integer.class,
                query.getParameters().toArray());
        return cnt == null ? 0 : cnt;
    }

    @Override
    public List<Favorite> query(FavoriteQuery query)
    {
        return jdbcTemplate.query("SELECT * FROM favorites " + query.getQueryString(),
                new BeanPropertyRowMapper<>(Favorite.class),
                query.getParameters().toArray());
    }
}
