$(function()
{
    let app_banner = new Vue(
    {
        el: "#banner",
        data:
        {
            books: [],
            index: 0
        },
        methods:
        {
            prev: function()
            {
                this.index = (this.index + this.books.length - 1) % this.books.length;
            },
            next: function()
            {
                this.index = (this.index + 1) % this.books.length;
            },
            select: function(index)
            {
                this.index = index;
            }
        },
        mounted: function()
        {
            // 随机获取4本电子书
            randomBooks(4,
                function(books)
                {
                    app_banner.books = books;
                }
            );
        }
    });

    let app_category_random = new Vue(
    {
        el: "#category_random",
        data:
        {
            categories: [],
            maxScoreBooks: [],
            randomBooks: []
        },
        methods:
        {

        },
        mounted: function()
        {
            // 获取所有类别
            queryCategories({},
            function(categories)
            {
                app_category_random.categories = categories;
                for (let c of categories)
                {
                    // 获取每个类别得分最高的电子书
                    queryBooks(
                    {
                        categoryId: c.id,
                        orderBy: "score",
                        pageSize: 1,
                        currentPage: 1
                    }, 
                    function(pageBean)
                    {
                        app_category_random.maxScoreBooks.push(pageBean.data[0]);
                    });

                    // 获取每个类别的随机6本电子书
                    randomBooks(6,
                        function(books)
                        {
                            app_category_random.randomBooks.push(books);
                        }
                    );
                }
            });
        }
    });
});