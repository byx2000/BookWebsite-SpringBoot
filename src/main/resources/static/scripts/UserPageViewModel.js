$(function()
{
    let app = new Vue(
        {
            el: "#content",
            data:
            {
                tabNames: ["我的评论", "我的收藏", "我的点赞"],
                tabLinks: ["./user_page.html?tab=my_comments&currentPage=1", 
                           "./user_page.html?tab=my_favorites&currentPage=1", 
                           "./user_page.html?tab=my_likes&currentPage=1"],
                selectedTabIndex: 0,
                currentPage: 1,
                totalPage: 0,
                totalCount: 0,
                user: null,
                comments: [],
                comment_books: [],
                favorites: [],
                jumpTo: function(page)
                {
                    let url = "./user_page.html?tab=";

                    if (this.selectedTabIndex === 0)
                        url += "my_comments";
                    else if (this.selectedTabIndex === 1)
                        url += "my_favorites";
                    else
                        url += "my_likes";
                    
                    url += "&currentPage=" + String(page);

                    location.href = url;
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
                },
                deleteComment(commentId)
                {
                    alert("功能未实现");
                }
            },
            mounted: function()
            {
                // 获取标签页
                let tabParam = getUrlParameter("tab");
                if (tabParam === "my_comments")
                    this.selectedTabIndex = 0;
                else if (tabParam === "my_favorites")
                    this.selectedTabIndex = 1;
                else
                    this.selectedTabIndex = 2;

                // 获取页码
                this.currentPage = Number(getUrlParameter("currentPage"));

                // 获取当前用户信息
                getCuurentUser(
                    function(user) // 已登录
                    {
                        app.user = user;
                        
                        if (app.selectedTabIndex === 0) // 我的评论
                        {
                            // 查询评论列表
                            queryComments(
                                {
                                    userId: user.id,
                                    pageSize: 10,
                                    currentPage: app.currentPage
                                },
                                function (pageBean)
                                {
                                    app.comments = pageBean.data;
                                    app.totalPage = pageBean.totalPage;
                                    app.totalCount = pageBean.totalCount;

                                    app.comment_books = new Array(app.comments.length);
                                    for (let i = 0; i < app.comments.length; ++i)
                                    {
                                        // 获取评论所属的电子书
                                        queryBooks(
                                            {
                                                bookId: app.comments[i].bookId
                                            },
                                            function(books)
                                            {
                                                app.comment_books.splice(i, 1, books[0]);
                                            }
                                        );
                                    }
                                }
                            );
                        }
                        else if (app.selectedTabIndex === 1) // 我的收藏
                        {
                            // 查询收藏列表
                            queryFavorites(
                                {
                                    userId: user.id,
                                    pageSize: 10,
                                    currentPage: app.currentPage
                                },
                                function (pageBean)
                                {
                                    app.totalPage = pageBean.totalPage;
                                    app.totalCount = pageBean.totalCount;
                                    app.favorites = pageBean.data;
                                    //alert(JSON.stringify(app.favorites_and_books));
                                }
                            );
                        }
                    },
                    function(msg) // 未登录
                    {
                        location.href = "./login.html";
                    }
                );
            }
        }
    );
});