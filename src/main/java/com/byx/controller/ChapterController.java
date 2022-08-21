package com.byx.controller;

import com.byx.annotation.RequireLogin;
import com.byx.domain.ResultInfo;
import com.byx.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 电子书章节控制器
 */
@RestController
@RequestMapping("/chapter")
@Validated
public class ChapterController extends BaseController {
    @Autowired
    private IChapterService chapterService;

    /**
     * 获取章节数据
     *
     * @param bookId  电子书id
     * @param chapter 章节
     * @return 章节数据
     */
    @RequestMapping("/data")
    @RequireLogin
    public ResultInfo chapter(@NotNull Integer bookId,
                              @NotNull Integer chapter) {
        return ResultInfo.success(chapterService.getChapterAndBook(bookId, chapter));
    }

    /**
     * 获取电子书目录
     *
     * @param bookId 电子书id
     * @return 目录
     */
    @RequestMapping("/contents")
    @RequireLogin
    public ResultInfo contents(@NotNull Integer bookId) {
        return ResultInfo.success(chapterService.getContents(bookId));
    }
}
