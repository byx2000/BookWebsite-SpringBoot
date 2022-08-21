package com.byx.controller;

import com.byx.annotation.RequireLogin;
import com.byx.domain.ResultInfo;
import com.byx.service.IBookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 书签控制器
 */
@RestController
@RequestMapping("/bookmark")
@Validated
public class BookmarkController extends BaseController {
    @Autowired
    private IBookmarkService bookmarkService;

    /**
     * 添加书签
     *
     * @param chapterId 章节id
     * @return 操作结果：成功或失败
     */
    @RequestMapping("/add")
    @RequireLogin
    public ResultInfo add(@NotNull Integer chapterId) {
        bookmarkService.add(getCurrentUser().getId(), chapterId);
        return ResultInfo.success();
    }

    /**
     * 移除书签
     *
     * @param chapterId 章节id
     * @return 操作结果：成功或失败
     */
    @RequestMapping("/remove")
    @RequireLogin
    public ResultInfo remove(@NotNull Integer chapterId) {
        bookmarkService.remove(getCurrentUser().getId(), chapterId);
        return ResultInfo.success();
    }

    /**
     * 是否添加了书签
     *
     * @param chapterId 章节id
     * @return true或false
     */
    @RequestMapping("/is_bookmark")
    @RequireLogin
    public ResultInfo isBookmark(@NotNull Integer chapterId) {
        return ResultInfo.success(bookmarkService.isBookmark(getCurrentUser().getId(), chapterId));
    }

    /**
     * 查询当前用户的书签列表
     *
     * @param pageSize    每页显示条数
     * @param currentPage 当前页码
     * @param bookName    书名搜索关键词
     * @param chapterName 章节名搜索关键词
     * @return 分页数据
     */
    @RequestMapping("/query")
    @RequireLogin
    public ResultInfo query(@NotNull Integer pageSize,
                            @NotNull Integer currentPage,
                            String bookName,
                            String chapterName,
                            Boolean isDesc) {
        if (bookName == null) bookName = "";
        if (chapterName == null) chapterName = "";
        if (isDesc == null) isDesc = true;
        return ResultInfo.success(bookmarkService.queryBookmarksOfUser(
                getCurrentUser().getId(), bookName, chapterName, isDesc, pageSize, currentPage));
    }
}
