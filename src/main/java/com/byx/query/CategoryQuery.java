package com.byx.query;

/**
 * 电子书类型查询类
 */
public class CategoryQuery extends Query
{
    private Integer categoryId;

    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    @Override
    protected void customizeQuery()
    {
        if (categoryId != null)
            addWhereCondition("id == ?", categoryId);
    }
}
