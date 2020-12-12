$(function()
{
    let app = new Vue(
    {
        el: "#book_filter",
        data: 
        {
            categories: [],
            updateTimes: ["全部", "最近三天", "最近一周", "最近一个月"],
            daysAgo: ["-1", "3", "7", "30"],
            orderBy: ["更新时间", "字数", "热度", "评分"],
            orderBySubmit: ["updateTime", "wordCount", "heat", "score"],
            books: [],
            selectedCategoryIndex: 0,
            selectedUpdateTimeIndex: 0,
            selectedOrderByIndex: 0,
            currentPage: 1,
            totalPage: 0,
            totalCount: 0,
            pagePreview: [],
            jumpTo: function(categoryIndex, updateTimeIndex, orderByIndex, currentPage)
            {
                location.href = "./category.html?categoryIndex=" + categoryIndex + "&updateTimeIndex=" + updateTimeIndex + "&orderByIndex=" + orderByIndex + "&currentPage=" + currentPage;
            }
        },
        methods:
        {
            // 改变类别选项
            onCategoryClick: function(categoryIndex)
            {
                this.jumpTo(categoryIndex, this.selectedUpdateTimeIndex, this.selectedOrderByIndex, 1);
            },
            // 改变更新时间选项
            onUpdateTimeClick: function(updateTimeIndex)
            {
                this.jumpTo(this.selectedCategoryIndex, updateTimeIndex, this.selectedOrderByIndex, 1);
            },
            // 改变排序依据
            onOrderByClick: function(orderByIndex)
            {
                this.jumpTo(this.selectedCategoryIndex, this.selectedUpdateTimeIndex, orderByIndex, 1);
            },
            // 上一页
            lastPage: function()
            {
                if (this.currentPage > 1)
                {
                    this.jumpTo(this.selectedCategoryIndex, this.selectedUpdateTimeIndex, this.selectedOrderByIndex, this.currentPage - 1);
                }
            },
            //下一页
            nextPage: function()
            {
                if (this.currentPage < this.totalPage)
                {
                    this.jumpTo(this.selectedCategoryIndex, this.selectedUpdateTimeIndex, this.selectedOrderByIndex, this.currentPage + 1);
                }
            },
            // 跳转到指定页
            toPage: function(page)
            {
                this.jumpTo(this.selectedCategoryIndex, this.selectedUpdateTimeIndex, this.selectedOrderByIndex, page);
            }
        },
        mounted: function()
        {
            // 从url获取初始值
            this.selectedCategoryIndex = Number(getUrlParameter("categoryIndex"));
            this.selectedUpdateTimeIndex = Number(getUrlParameter("updateTimeIndex"));
            this.selectedOrderByIndex = Number(getUrlParameter("orderByIndex"));
            this.currentPage = Number(getUrlParameter("currentPage"));

            //查询所有类别、查询电子书
            request(CATEGORY_QUERY_URL, {})
                .then(categories =>
                {
                    app.categories = categories;
                    return request(BOOK_QUERY_URL, 
                        {
                            categoryId: categories[app.selectedCategoryIndex].id,
                            daysAgo: app.daysAgo[app.selectedUpdateTimeIndex],
                            pageSize: 15,
                            currentPage: app.currentPage,
                            orderBy: app.orderBySubmit[app.selectedOrderByIndex]
                        });
                })
                .then(pageBean =>
                {
                    app.totalPage = pageBean.totalPage;
                    app.totalCount = pageBean.totalCount;
                    app.books = pageBean.data;
                    app.pagePreview = pageBean.pagePreview;
                });
        }
    });
});