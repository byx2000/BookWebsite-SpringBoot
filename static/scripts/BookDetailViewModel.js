$(function()
{
    // 加载vue组件
    let app = new Vue(
    {
        el: "#content",
        data: 
        {
            bookId: null,
            book: null,
            category: null,
            commentsAndBooks: [],
            currentPage: 1,
            totalCount: 0,
            totalPage: 0,
            pagePreview: [],
            users: [],
            recommends: [],
            commentText: "",
            isFavorite: false,
            favoriteId: null,
            isLike: false,
            isDislike: false
        },
        methods:
        {
            addComment: function()
            {
                if (this.commentText.trim() === "")
                {
                    alert("评论不能为空！");
                    return;
                }

                // 发表评论
                publishComment(this.book.id, this.commentText, 
                    function()
                    {
                        location.reload();
                    }
                );
            },
            favorite: function()
            {
                addFavorite(this.book.id, 
                    function()
                    {
                        location.reload();
                    }
                );
            },
            unfavorite: function()
            {
                cancelFavorite(this.book.id, 
                    function()
                    {
                        location.reload();
                    }
                );
            },
            like: function()
            {
                evaluate(this.book.id, "like",
                    function()
                    {
                        app.book.likeCount++;
                        if (app.isDislike) app.book.dislikeCount--;
                        app.isLike = true;
                        app.isDislike = false;
                        
                    }
                );
            },
            dislike: function()
            {
                evaluate(this.book.id, "dislike",
                    function()
                    {
                        app.book.dislikeCount++;
                        if (app.isLike) app.book.likeCount--;
                        app.isDislike = true;
                        app.isLike = false;
                    }
                );
            },
            cancelLike: function()
            {
                evaluate(this.book.id, "cancelLike",
                    function()
                    {
                        app.isLike = false;
                        app.book.likeCount--;
                    }
                );
            },
            cancelDislike: function()
            {
                evaluate(this.book.id, "cancelDislike",
                    function()
                    {
                        app.isDislike = false;
                        app.book.dislikeCount--;
                    }
                );
            },
            read: function()
            {
                // 获取当前登录用户
                getCuurentUser(function()
                {
                    // 打开新的阅读标签页
                    window.open("./read.html?bookId=" + app.book.id + "&chapter=1");
                });
            },
            lastPage: function()
            {
                if (this.currentPage > 1)
                {
                    queryComments(
                        {
                            bookId: this.bookId,
                            pageSize: 5,
                            currentPage: app.currentPage - 1
                        },
                        function(pageBean)
                        {
                            app.commentsAndBooks = pageBean.data;
                            app.totalCount = pageBean.totalCount;
                            app.totalPage = pageBean.totalPage;
                            app.pagePreview = pageBean.pagePreview;
                            app.currentPage--;
                        }
                    );
                }
            },
            nextPage: function()
            {
                if (this.currentPage < this.totalPage)
                {
                    queryComments(
                        {
                            bookId: this.bookId,
                            pageSize: 5,
                            currentPage: app.currentPage + 1
                        },
                        function(pageBean)
                        {
                            app.commentsAndBooks = pageBean.data;
                            app.totalCount = pageBean.totalCount;
                            app.totalPage = pageBean.totalPage;
                            app.pagePreview = pageBean.pagePreview;
                            app.currentPage++;
                        }
                    );
                }
            },
            toPage: function(page)
            {
                queryComments(
                    {
                        bookId: this.bookId,
                        pageSize: 5,
                        currentPage: page
                    },
                    function(pageBean)
                    {
                        app.commentsAndBooks = pageBean.data;
                        app.totalCount = pageBean.totalCount;
                        app.totalPage = pageBean.totalPage;
                        app.pagePreview = pageBean.pagePreview;
                        app.currentPage = page;
                    }
                );
            }
        },
        mounted: function()
        {
            // 从url获取电子书id
            this.bookId = getUrlParameter("bookId");

            // 获取电子书详情
            queryBooks(
                {
                    bookId: this.bookId
                },
                function(books)
                {
                    app.book = books[0];

                    // 获取类别信息
                    queryCategories(
                        {
                            categoryId: app.book.categoryId
                        },
                        function(category)
                        {
                            app.category = category;
                        }
                    );

                    // 获取同类推荐列表
                    queryBooks(
                        {
                            categoryId: books[0].categoryId,
                            orderBy: "score",
                            limit: 6
                        },
                        function(books)
                        {
                            app.recommends = books;
                        }
                    );
                }
            );
        
            // 获取评论
            queryComments(
                {
                    bookId: this.bookId,
                    pageSize: 5,
                    currentPage: this.currentPage
                },
                function(pageBean)
                {
                    app.commentsAndBooks = pageBean.data;
                    app.totalCount = pageBean.totalCount;
                    app.totalPage = pageBean.totalPage;
                    app.pagePreview = pageBean.pagePreview;
                }
            );

            // 查询当前用户是否已收藏当前电子书
            isFavorite(this.bookId,
                function(res)
                {
                    app.isFavorite = res;
                },
                function(){}
            );

            // 查询是否已点赞
            isLike(this.bookId,
                function(result)
                {
                    app.isLike = result;
                },
                function(){}
            );

            // 查询是否已点踩
            isDislike(this.bookId,
                function(result)
                {
                    app.isDislike = result;
                },
                function(){}
            );
        }
    });
});