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
                pagePreview: [],
                user: null,
                commentsAndBooks: [],
                favoritesAndBooks: [],
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
                toPage: function(page)
                {
                    this.jumpTo(page);
                },
                deleteComment(commentId)
                {
                    if (confirm("您确定要删除该条评论吗？"))
                    {
                        deleteComment(commentId,
                            function()
                            {
                                location.reload();
                            }
                        );
                    }
                },
                unfavorite: function(bookId)
                {
                    cancelFavorite(bookId, 
                        function()
                        {
                            location.reload();
                        }
                    );
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
                                    app.commentsAndBooks = pageBean.data;
                                    app.totalPage = pageBean.totalPage;
                                    app.totalCount = pageBean.totalCount;
                                    app.pagePreview = pageBean.pagePreview;
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
                                    app.favoritesAndBooks = pageBean.data;
                                    app.pagePreview = pageBean.pagePreview;
                                }
                            );
                        }
                    },
                    function() // 未登录
                    {
                        // 跳转到登录页面
                        location.href = "./login.html";
                    }
                );
            }
        }
    );
});