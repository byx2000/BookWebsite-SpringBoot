package com.byx.dao;

import com.byx.domain.Favorite;
import com.byx.domain.PageBean;
import com.byx.query.Query;

import java.util.List;

/**
 * 收藏数据访问接口
 */
public interface IFavoriteDao {
    /**
     * 根据条件查询收藏数量
     *
     * @param query 查询条件
     * @return 结果总数
     */
    int count(Query query);

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 收藏列表
     */
    List<Favorite> query(Query query);

    /**
     * 分页查询
     *
     * @param query       查询条件
     * @param pageSize    每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<Favorite> queryByPage(Query query, int pageSize, int currentPage);

    /**
     * 保存收藏记录
     *
     * @param favorite 收藏记录
     */
    void save(Favorite favorite);

    /**
     * 删除收藏记录
     *
     * @param query 条件
     */
    void delete(Query query);
}
