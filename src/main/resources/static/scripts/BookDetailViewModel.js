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
            commentText: ""
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
                        getSimilarRecommend(
                            books[0].categoryId, 
                            comments.length === 0 ? 4 : 6,
                            function(books)
                            {
                                app.recommends = books;
                            }
                        );
                    }
                );
            });
        }
    });
});