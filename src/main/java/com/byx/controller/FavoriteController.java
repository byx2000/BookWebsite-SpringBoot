package com.byx.controller;

import com.byx.annotation.RequireLogin;
import com.byx.domain.PageBean;
import com.byx.domain.ResultInfo;
import com.byx.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 收藏控制器
 */
@RestController
@RequestMapping("/favorite")
@Validated
public class FavoriteController extends BaseController
{
    @Autowired
    private IFavoriteService favoriteService;

    /**
     * 查询当前用户收藏记录
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @param bookName 书名搜索关键词
     * @param author 作者搜索关键词
     * @param isDesc 是否按照时间降序排序
     * @return 分页数据
     */
    @RequestMapping("/query")
    @RequireLogin
    public ResultInfo query(@NotNull Integer pageSize,
                            @NotNull Integer currentPage,
                            String bookName,
                            String author,
                            Boolean isDesc)
    {
        if (bookName == null) bookName = "";
        if (author == null) author = "";
        if (isDesc == null) isDesc = true;
        PageBean<List<Object>> pageBean = favoriteService.queryFavoritesOfUser(
                getCurrentUser().getId(), bookName, author, isDesc, pageSize, currentPage);
        return ResultInfo.success(pageBean);
    }

    /**
     * 判断当前用户是否收藏了指定电子书
     * @param bookId 电子书
     * @return true或false
     */
    @RequestMapping("/isFavorite")
    @RequireLogin
    public ResultInfo isFavorite(@NotNull Integer bookId)
    {
        return ResultInfo.success(favoriteService.isFavorite(bookId, getCurrentUser().getId()));
    }

    /**
     * 添加收藏
     * @param bookId 电子书id
     * @return 操作结果：成功或失败
     */
    @RequestMapping("/add")
    @RequireLogin
    public ResultInfo add(@NotNull Integer bookId)
    {
        favoriteService.add(bookId, getCurrentUser().getId());
        return ResultInfo.success();
    }

    /**
     * 取消收藏
     * @param bookId 电子书id
     * @return 操作结果：成功或失败
     */
    @RequestMapping("/cancel")
    @RequireLogin
    public ResultInfo cancel(@NotNull Integer bookId)
    {
        favoriteService.cancel(bookId, getCurrentUser().getId());
        return ResultInfo.success();
    }
}
