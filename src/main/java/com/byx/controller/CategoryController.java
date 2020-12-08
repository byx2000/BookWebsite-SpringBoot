package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询电子书类型
     * @param categoryId 类型id
     * @return 如果指定了categoryId参数，则返回id为categoryId的类型信息，否则返回所有类型列表
     */
    @RequestMapping("/query")
    public ResultInfo query(Integer categoryId)
    {
        // 查询所有类型
        if (categoryId == null)
        {
            return ResultInfo.success(categoryService.queryAll());
        }
        // 根据id查询类型
        else
        {
            return ResultInfo.success(categoryService.queryById(categoryId));
        }
    }
}
