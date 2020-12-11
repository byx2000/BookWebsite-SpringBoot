package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.query.BookQueryCondition;
import com.byx.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController extends BaseController
{
    @Autowired
    private IBookService bookService;

    /**
     * 根据条件查询电子书
     * @param bookQueryCondition 查询条件
     * @param pageSize 每页显示的条数
     * @param currentPage 当前页码
     * @return 结果
     */
    @RequestMapping("/query")
    public ResultInfo query(BookQueryCondition bookQueryCondition,
                            Integer pageSize,
                            Integer currentPage)
    {
        // 不带分页的查询
        if (pageSize == null || currentPage == null)
        {
            return ResultInfo.success(bookService.query(bookQueryCondition));
        }
        // 带分页的查询
        else
        {
            return ResultInfo.success(bookService.queryByPage(bookQueryCondition, pageSize, currentPage));
        }
    }
}
