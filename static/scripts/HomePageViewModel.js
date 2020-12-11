$(function()
{
    let app = new Vue(
    {
        el: "#holder",
        data:
        {
            books: [],
            index: 0,
            categories: [],
            maxScoreBooks: [],
            randomBooks: []
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
            request(BOOK_QUERY_URL, { orderBy: "random", limit: 4 })
                .then(books =>
                {
                    app.books = books;
                });

            // 获取所有类别
            request(CATEGORY_QUERY_URL, {})
                .then(categories =>
                {
                    app.categories = categories;
                    app.maxScoreBooks = new Array(categories.length);
                    app.randomBooks = new Array(categories.length);

                    for (let i = 0; i < categories.length; ++i)
                    {
                        // 获取每个类别得分最高的电子书
                        request(BOOK_QUERY_URL, { categoryId: categories[i].id, orderBy: "score", limit: 1 })
                            .then(books =>
                            {
                                app.maxScoreBooks.splice(i, 1, books[0]);
                            });
                        // 获取每个类别的随机6本电子书
                        request(BOOK_QUERY_URL, { categoryId: categories[i].id, orderBy: "random", limit: 6 })
                            .then(books =>
                            {
                                app.randomBooks.splice(i, 1, books);
                            });
                    }
                });
        }
    });
});