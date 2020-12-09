package com.byx.domain;

import java.util.List;

/**
 * 封装分页数据
 * @param <T> 列表项数据类型
 */
public class PageBean<T>
{
    private int totalCount;
    private int currentPage;
    private int pageSize;
    private List<T> data;

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public int getTotalPage()
    {
        if (pageSize == 0) return 0;
        return (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", data=" + data +
                '}';
    }
}
