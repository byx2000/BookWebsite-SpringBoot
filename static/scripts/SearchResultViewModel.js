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
            request(BOOK_QUERY_URL, { keyword: this.keyword, pageSize: 10, currentPage: this.currentPage })
                .then(pageBean =>
                {
                    app.books = pageBean.data;
                    app.totalCount = pageBean.totalCount;
                    app.totalPage = pageBean.totalPage;
                    app.pagePreview = pageBean.pagePreview;
                });

            // 获取搜索建议
            request(BOOK_QUERY_URL, { orderBy: "heat", limit: 20 })
                .then(books =>
                {
                    app.suggestions = books;
                });
        }
    });
});