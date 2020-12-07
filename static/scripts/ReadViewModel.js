$(function()
{
    let app = new Vue(
        {
            el: "#content",
            data:
            {
                bookId: 0,
                currentPage: 1,
                totalCount: 0,
                totalPage: 0,
                text: "",
                jumpTo: function(page)
                {
                    location.href = "./read.html?bookId=" + this.bookId + "&currentPage=" + page;
                }
            },
            methods:
            {
                lastPage: function()
                {
                    if (this.currentPage > 1)
                    {
                        this.jumpTo(this.currentPage - 1);
                    }
                },
                nextPage: function()
                {
                    if (this.currentPage < this.totalPage)
                    {
                        this.jumpTo(this.currentPage + 1);
                    }
                }
            },
            mounted: function()
            {
                this.bookId = Number(getUrlParameter("bookId"));
                this.currentPage = Number(getUrlParameter("currentPage"));

                read(this.bookId, this.currentPage,
                    function(pageBean)
                    {
                        app.totalCount = pageBean.totalCount;
                        app.totalPage = pageBean.totalPage;
                        app.text = pageBean.data[0];
                    }
                );
            }
        }
    );
});