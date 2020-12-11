package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.service.IBookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 书签控制器
 */
@RestController
@RequestMapping("/bookmark")
public class BookmarkController extends BaseController
{
    @Autowired
    private IBookmarkService bookmarkService;

    @RequestMapping("/add")
    public ResultInfo add(Integer bookId, Integer chapter)
    {
        // 参数校验
        if (bookId == null || chapter == null) return ResultInfo.fail("参数错误");

        // 登录校验
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        // 添加书签
        bookmarkService.addBookmark(user.getId(), bookId, chapter);
        return ResultInfo.success();
    }
}
