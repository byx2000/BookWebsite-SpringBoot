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
            orderMethod: "默认",
            jumpTo: function(page)
            {
                location.href = "./search_result.html?keyword=" + this.keyword + "&currentPage=" + page + "&orderMethod=" + this.orderMethod;
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
            },
            // 排序选项改变
            orderMethodChanged: function()
            {
                this.jumpTo(1);
            }
        },
        mounted: function()
        {
            // 从url获取搜索词和页码
            this.keyword = getUrlParameter("keyword");
            this.currentPage = Number(getUrlParameter("currentPage"));

            // 从url获取排序依据
            this.orderMethod = getUrlParameter("orderMethod");
            if (this.orderMethod === null) this.orderMethod = "默认";

            // 获取搜索结果
            request(BOOK_QUERY_URL, 
                { 
                    keyword: this.keyword, 
                    pageSize: 10, 
                    currentPage: this.currentPage,
                    orderBy: this.orderMethod === "按热度排序" ? "heat" : (this.orderMethod === "按评分排序" ? "score" : "default")
                })
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