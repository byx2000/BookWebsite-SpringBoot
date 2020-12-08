$(function()
{
    // 加载vue组件
    let app = new Vue(
    {
        el: "#content",
        data: 
        {
            book: null,
            category: null,
            comments: [],
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

                // 添加评论
                saveComment(this.book.id, this.commentText, 
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
                cancelFavorite(this.favoriteId, 
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
            }
        },
        mounted: function()
        {
            // 从url获取电子书id
            let bookId = getUrlParameter("bookId");

            // 获取电子书详情
            queryBooks(
                {
                    bookId: bookId
                },
                function(books)
                {
                    app.book = books[0];

                    // 获取类别信息
                    queryCategories(
                        {
                            categoryId: app.book.categoryId
                        },
                        function(categories)
                        {
                            app.category = categories[0];
                        }
                    );

                    // 获取评论
                    queryComments(
                        {
                            bookId: books[0].id
                        },
                        function(comments)
                        {
                            app.comments = comments;

                            app.users = new Array(comments.length);
                            
                            for (let i = 0; i < comments.length; ++i)
                            {
                                // 获取用户信息
                                queryUsers(
                                    {
                                        userId: comments[i].userId
                                    },
                                    function(users)
                                    {
                                        // 这里不能用app.users[i] = users[0];
                                        // 因为这样会导致Vue无法及时更新视图
                                        app.users.splice(i, 1, users[0]);
                                        //console.log(JSON.stringify(users[0], null, '\t'));
                                        //console.log(JSON.stringify(app.users, null, '\t'));
                                    }
                                );
                            }

                            // 获取同类推荐列表
                            queryBooks(
                                {
                                    categoryId: books[0].categoryId,
                                    orderBy: "score",
                                    limit: comments.length === 0 ? 4 : 6,
                                },
                                function(books)
                                {
                                    app.recommends = books;
                                }
                            );
                        }
                    );

                    // 查询当前用户是否已收藏当前电子书
                    queryFavorites(
                        {
                            bookId: bookId,
                            pageSize: 1,
                            currentPage: 1
                        },
                        function (pageBean)
                        {
                            if (pageBean.data.length > 0)
                            {
                                app.isFavorite = true;
                                app.favoriteId = pageBean.data[0][0].id;
                            }
                        },
                        function (){}
                    );

                    // 查询是否已赞或踩当前电子书
                    isLike(books[0].id,
                        function(result)
                        {
                            app.isLike = result;
                        },
                        function(){}
                    );

                    isDislike(books[0].id,
                        function(result)
                        {
                            app.isDislike = result;
                        },
                        function(){}
                    );
                }
            );
        }
    });
});