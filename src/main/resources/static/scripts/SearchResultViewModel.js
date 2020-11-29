$(function()
{
    let app = new Vue(
    {
        el: "#content",
        data:
        {
            keyword: "",
            books: [],
            currentPage: 1,
            totalPage: 0,
            totalCount: 0,
            pagePreview: [],
            suggestions: [],
            jumpTo: function(page)
            {
                location.href = "./search_result.html?keyword=" + this.keyword + "&currentPage=" + page;
            }
        },
        methods:
        {
            // 上一页
            lastPage: function()
            {
                if (this.currentPage > 1)
                {
                    this.jumpTo(this.currentPage - 1);
                }
            },
            //下一页
            nextPage: function()
            {
                if (this.currentPage < this.totalPage)
                {
                    this.jumpTo(this.currentPage + 1);
                }
            },
            // 跳转到指定页
            toPage: function(page)
            {
                this.jumpTo(page);
            }
        },
        mounted: function()
        {
            // 从url获取搜索词和页码
            this.keyword = getUrlParameter("keyword");
            this.currentPage = Number(getUrlParameter("currentPage"));

            // 获取搜索结果
            queryBooks(
                {
                    keyword: this.keyword,
                    pageSize: 12,
                    currentPage: this.currentPage
                },
                function(pageBean)
                {
                    app.books = pageBean.data;
                    app.totalCount = pageBean.totalCount;
                    app.totalPage = pageBean.totalPage;

                    // 计算要显示的页码
                    app.pagePreview = [];
                    if (app.currentPage == 1)
                    {
                        for (let i = 1; i <= Math.min(app.totalPage, 3); ++i)
                        {
                            app.pagePreview.push(i);
                        }
                    }
                    else if (app.currentPage == app.totalPage)
                    {
                        for (let i = Math.max(1, app.totalPage - 2); i <= app.totalPage; ++i)
                        {
                            app.pagePreview.push(i);
                        }
                    }
                    else
                    {
                        app.pagePreview.push(app.currentPage - 1);
                        app.pagePreview.push(app.currentPage);
                        app.pagePreview.push(app.currentPage + 1);
                    }
                }
            );

            // 获取搜索建议
            /*queryBooks(
                {
                    random: 4
                }, 
                function(books)
                {
                    app.suggestions = books;
                }
            );*/
            getSearchSuggestion(this.keyword, 20, 
                function(books)
                {
                    app.suggestions = books;
                }
            );
        }
    });
});