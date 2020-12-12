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
                // 上一页
                lastPage: function()
                {
                    if (this.currentPage > 1)
                    {
                        this.jumpTo(this.currentPage - 1);
                    }
                },
                // 下一页
                nextPage: function()
                {
                    if (this.currentPage < this.totalPage)
                    {
                        this.jumpTo(this.currentPage + 1);
                    }
                },
                // 跳转到指定页
                toPage: function(page)
                {
                    this.jumpTo(page);
                },
                // 删除评论
                deleteComment(commentId)
                {
                    if (confirm("您确定要删除该条评论吗？"))
                    {
                        request(DELETE_COMMENT_URL, { commentId: commentId })
                            .then(() =>
                            {
                                location.reload();
                            });
                    }
                },
                // 取消收藏
                unfavorite: function(bookId)
                {
                    request(UNFAVORITE_URL, { bookId: bookId })
                        .then(() =>
                        {
                            location.reload();
                        });
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

                // 当前处于“我的评论”标签页
                if (this.selectedTabIndex === 0)
                {
                    request(GET_CURRENT_USER_URL, {})
                        .then(user =>
                        {
                            app.user = user;
                            return request(COMMENT_QUERY_URL, { userId: user.id, pageSize: 10, currentPage: app.currentPage });
                        })
                        .then(pageBean =>
                        {
                            app.commentsAndBooks = pageBean.data;
                            app.totalPage = pageBean.totalPage;
                            app.totalCount = pageBean.totalCount;
                            app.pagePreview = pageBean.pagePreview;
                        })
                        .catch(() =>
                        {
                            location.href = "./login.html";
                        });
                }
                // 当前处于“我的收藏”标签页
                else if (this.selectedTabIndex === 1)
                {
                    request(GET_CURRENT_USER_URL, {})
                        .then(user =>
                        {
                            app.user = user;
                            return request(FAVORITE_QUERY_URL, { userId: user.id, pageSize: 10, currentPage: app.currentPage });
                        })
                        .then(pageBean =>
                        {
                            app.favoritesAndBooks = pageBean.data;
                            app.totalPage = pageBean.totalPage;
                            app.totalCount = pageBean.totalCount;
                            app.pagePreview = pageBean.pagePreview;
                        })
                        .catch(() =>
                        {
                            location.href = "./login.html";
                        });
                }
            }
        }
    );
});