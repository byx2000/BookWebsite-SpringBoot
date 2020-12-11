package com.byx.domain;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Integer> getPagePreview()
    {
        int maxPreviewCount = 5;
        int start = Math.max(1, getCurrentPage() - 2);
        int end = Math.min(getTotalPage(), getCurrentPage() + 2);

        List<Integer> pages = new ArrayList<>();
        for (int i = start; i <= end; ++i)
        {
            pages.add(i);
        }

        if (pages.size() < maxPreviewCount)
        {
            start--;
            while (pages.size() < maxPreviewCount && start >= 1)
            {
                pages.add(start--);
            }
        }

        if (pages.size() < maxPreviewCount)
        {
            end++;
            while (pages.size() < maxPreviewCount && end <= getTotalPage())
            {
                pages.add(end++);
            }
        }

        Collections.sort(pages);

        return pages;
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
