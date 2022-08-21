package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.query.BookQueryCondition;
import com.byx.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/book")
@Validated
public class BookController extends BaseController {
    @Autowired
    private IBookService bookService;

    /**
     * 根据条件查询电子书
     *
     * @param bookQueryCondition 查询条件
     * @param pageSize           每页显示的条数（可选）
     * @param currentPage        当前页码（可选）
     * @return 电子书列表或分页数据
     */
    @RequestMapping("/query")
    public ResultInfo query(BookQueryCondition bookQueryCondition,
                            Integer pageSize,
                            Integer currentPage) {
        // 不带分页的查询
        if (pageSize == null || currentPage == null) {
            return ResultInfo.success(bookService.query(bookQueryCondition));
        }
        // 带分页的查询
        else {
            return ResultInfo.success(bookService.queryByPage(bookQueryCondition, pageSize, currentPage));
        }
    }

    /**
     * 搜索预测
     *
     * @param keyword 关键词
     * @param count   数量
     * @return 预测列表
     */
    @RequestMapping("/search_predict")
    public ResultInfo searchPredict(@NotNull String keyword,
                                    @NotNull Integer count) {
        return ResultInfo.success(bookService.searchPredict(keyword, count));
    }
}
