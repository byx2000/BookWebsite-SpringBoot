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

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 收藏控制器
 */
@RestController
@RequestMapping("/favorite")
public class FavoriteController
{
    @Autowired
    private IFavoriteService favoriteService;

    @Autowired
    private HttpSession session;

    @RequestMapping("/query")
    public ResultInfo query(FavoriteQuery favoriteQuery, Integer pageSize, Integer currentPage)
    {
        // 获取当前登录用户信息
        User user = (User) session.getAttribute("user");
        if (user == null) return ResultInfo.fail("当前未登录");

        // 只允许访问当前用户的收藏
        favoriteQuery.setUserId(user.getId());

        // 不带分页的查询
        if (pageSize == null || currentPage == null)
        {
            List<Favorite> favorites = favoriteService.query(favoriteQuery);
            return ResultInfo.success(favorites);
        }
        // 带分页的查询
        else
        {
            PageBean<List<Object>> pageBean = favoriteService.queryByPage(favoriteQuery, pageSize, currentPage);
            return ResultInfo.success(pageBean);
        }
    }
}
