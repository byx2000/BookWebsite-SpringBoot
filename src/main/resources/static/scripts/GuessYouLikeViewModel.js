$(function()
{
    let app = new Vue(
    {
        el: "#guess_you_like",
        data:
        {
            books: []
        },
        methods:
        {
            // 刷新推荐列表
            refresh: function()
            {
                // 获取搜索建议
                queryBooks(
                {
                    random: 4
                }, 
                function(books)
                {
                    app.books = books;
                },
                function(errMsg)
                {
                    alert(errMsg);
                });
            }
        },
        mounted: function()
        {
            // 获取随机电子书
            queryBooks(
            {
                random: 4
            }, 
            function(books)
            {
                app.books = books;
            },
            function(errMsg)
            {
                alert(errMsg);
            });
        }
    });
});