package com.byx.controller;

import com.byx.domain.Category;
import com.byx.domain.ResultInfo;
import com.byx.query.CategoryQuery;
import com.byx.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 根据条件查询电子书类型
     * @param categoryQuery 查询条件
     * @return 结果
     */
    @RequestMapping("/query")
    public ResultInfo query(CategoryQuery categoryQuery)
    {
        List<Category> categories = categoryService.query(categoryQuery);
        return ResultInfo.success(categories);
    }
}
