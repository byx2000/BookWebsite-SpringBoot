package com.byx.controller;

import com.byx.domain.Favorite;
import com.byx.domain.PageBean;
import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.query.FavoriteQuery;
import com.byx.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收藏控制器
 */
@RestController
@RequestMapping("/favorite")
public class FavoriteController extends BaseController
{
    @Autowired
    private IFavoriteService favoriteService;

    @RequestMapping("/query")
    public ResultInfo query(FavoriteQuery favoriteQuery, Integer pageSize, Integer currentPage)
    {
        // 参数校验
        if (pageSize == null || currentPage == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        // 只允许访问当前用户的收藏
        favoriteQuery.setUserId(user.getId());

        PageBean<List<Object>> pageBean = favoriteService.queryByPage(favoriteQuery, pageSize, currentPage);
        return ResultInfo.success(pageBean);
    }

    @RequestMapping("/add")
    public ResultInfo add(Favorite favorite)
    {
        // 参数校验
        if (favorite.getBookId() == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        // 只允许操作当前用户的收藏
        favorite.setUserId(user.getId());

        // 保存
        favoriteService.add(favorite);
        return ResultInfo.success();
    }
}
