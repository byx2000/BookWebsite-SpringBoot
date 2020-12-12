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
            //发表评论
            addComment: function()
            {
                if (this.commentText.trim() === "")
                {
                    alert("评论不能为空！");
                    return;
                }

                request(PUBLISH_COMMENT_URL, { bookId: this.bookId, content: this.commentText })
                    .then(() =>
                    {
                        location.reload();
                    })
                    .catch(error =>
                    {
                        alert(error);
                    });
            },
            // 收藏
            favorite: function()
            {
                request(FAVORITE_URL, { bookId: this.bookId })
                    .then(() =>
                    {
                        app.isFavorite = true;
                    })
                    .catch(error =>
                    {
                        alert(error);
                    });
            },
            // 取消收藏
            unfavorite: function()
            {
                request(UNFAVORITE_URL, { bookId: this.bookId })
                    .then(() =>
                    {
                        app.isFavorite = false;
                    })
                    .catch(error =>
                    {
                        alert(error);
                    });
            },
            // 点赞
            like: function()
            {
                request(EVALUATE_URL, { bookId: this.bookId, cmd: "like" })
                    .then(() =>
                    {
                        app.book.likeCount++;
                        if (app.isDislike) app.book.dislikeCount--;
                        app.isLike = true;
                        app.isDislike = false;
                    })
                    .catch(error =>
                    {
                        alert(error);
                    });
            },
            // 点踩
            dislike: function()
            {
                request(EVALUATE_URL, { bookId: this.bookId, cmd: "dislike" })
                    .then(() =>
                    {
                        app.book.dislikeCount++;
                        if (app.isLike) app.book.likeCount--;
                        app.isDislike = true;
                        app.isLike = false;
                    })
                    .catch(error =>
                    {
                        alert(error);
                    });
            },
            // 取消点赞
            cancelLike: function()
            {
                request(EVALUATE_URL, { bookId: this.bookId, cmd: "cancelLike" })
                    .then(() =>
                    {
                        app.isLike = false;
                        app.book.likeCount--;
                    })
                    .catch(error =>
                    {
                        alert(error);
                    });
            },
            // 取消点踩
            cancelDislike: function()
            {
                request(EVALUATE_URL, { bookId: this.bookId, cmd: "cancelDislike" })
                    .then(() =>
                    {
                        app.isDislike = false;
                        app.book.dislikeCount--;
                    })
                    .catch(error =>
                    {
                        alert(error);
                    });
            },
            // 在线阅读
            read: function()
            {
                // 如果已登录，则跳转到阅读页面
                request(GET_CURRENT_USER_URL, {})
                    .then(() =>
                    {
                        window.open("./read.html?bookId=" + app.book.id + "&chapter=1");
                    })
                    .catch(error=>
                    {
                        alert(error);
                    });
            },
            // 上一页
            lastPage: function()
            {
                if (this.currentPage > 1)
                {
                    request(COMMENT_QUERY_OF_BOOK_URL, { bookId: this.bookId, pageSize: 5, currentPage: app.currentPage - 1 })
                        .then(pageBean =>
                        {
                            app.commentsAndBooks = pageBean.data;
                            app.totalCount = pageBean.totalCount;
                            app.totalPage = pageBean.totalPage;
                            app.pagePreview = pageBean.pagePreview;
                            app.currentPage--;
                        });
                }
            },
            // 下一页
            nextPage: function()
            {
                if (this.currentPage < this.totalPage)
                {
                    request(COMMENT_QUERY_OF_BOOK_URL, { bookId: this.bookId, pageSize: 5, currentPage: app.currentPage + 1 })
                        .then(pageBean =>
                        {
                            app.commentsAndBooks = pageBean.data;
                            app.totalCount = pageBean.totalCount;
                            app.totalPage = pageBean.totalPage;
                            app.pagePreview = pageBean.pagePreview;
                            app.currentPage++;
                        });
                }
            },
            // 跳转到指定页
            toPage: function(page)
            {
                request(COMMENT_QUERY_OF_BOOK_URL, { bookId: this.bookId, pageSize: 5, currentPage: page })
                    .then(pageBean =>
                    {
                        app.commentsAndBooks = pageBean.data;
                        app.totalCount = pageBean.totalCount;
                        app.totalPage = pageBean.totalPage;
                        app.pagePreview = pageBean.pagePreview;
                        app.currentPage = page;
                    });
            }
        },
        mounted: function()
        {
            // 从url获取电子书id
            this.bookId = getUrlParameter("bookId");
            
            // 获取电子书详情、类别详情、同类推荐
            request(BOOK_QUERY_URL, { bookId: this.bookId })                
                .then(books =>
                {
                    app.book = books[0];
                    return Promise.all([
                        request(CATEGORY_QUERY_URL, { categoryId: books[0].categoryId }),
                        request(BOOK_QUERY_URL, { categoryId: books[0].categoryId, orderBy: "score", limit: 6 })
                    ]);
                })
                .then(([category, books]) =>
                {
                    app.category = category;
                    app.recommends = books;

                });
                
            // 获取评论
            request(COMMENT_QUERY_OF_BOOK_URL, { bookId: this.bookId, pageSize: 5, currentPage: this.currentPage })
                .then(pageBean =>
                {
                    app.commentsAndBooks = pageBean.data;
                    app.totalCount = pageBean.totalCount;
                    app.totalPage = pageBean.totalPage;
                    app.pagePreview = pageBean.pagePreview;
                });

            // 查询当前用户是否已收藏当前电子书
            request(IS_FAVORITE_URL, { bookId: this.bookId })
                .then(isFavorite =>
                {
                    app.isFavorite = isFavorite;
                })
                .catch(()=>{});

            // 查询是否已点赞
            request(IS_LIKE_URL, { bookId: this.bookId })
                .then(isLike =>
                {
                    app.isLike = isLike;
                })
                .catch(()=>{});

            // 查询是否已点踩
            request(IS_DISLIKE_URL, { bookId: this.bookId })
                .then(isDislike =>
                {
                    app.isDislike = isDislike;
                })
                .catch(()=>{});
        }
    });
});