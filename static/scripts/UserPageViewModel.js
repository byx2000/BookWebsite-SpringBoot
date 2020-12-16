$(function()
{
    let app = new Vue(
        {
            el: "#content",
            data:
            {
                tabNames: ["我的评论", "我的收藏", "我的书签"],
                tabLinks: ["./user_page.html?tab=my_comments&currentPage=1", 
                           "./user_page.html?tab=my_favorites&currentPage=1", 
                           "./user_page.html?tab=my_bookmarks&currentPage=1"],
                selectedTabIndex: 0,
                currentPage: 1,
                totalPage: 0,
                totalCount: 0,
                pagePreview: [],
                user: null,
                commentsAndBooks: [],
                favoritesAndBooks: [],
                bookmarksAndBooksAndChapters: [],
                bookNameKeyword: "",
                commentContentKeyword: "",
                authorKeyword: "",
                chapterNameKeyword: "",
                timeOrder: "按时间降序排序",
                jumpTo: function(page)
                {
                    let url = "./user_page.html?tab=";

                    if (this.selectedTabIndex === 0)
                    {
                        url += "my_comments";
                        url += "&bookName=" + this.bookNameKeyword;
                        url += "&commentContent=" + this.commentContentKeyword;
                        url += "&timeOrder=" + this.timeOrder;
                    }
                        
                    else if (this.selectedTabIndex === 1)
                    {
                        url += "my_favorites";
                        url += "&bookName=" + this.bookNameKeyword;
                        url += "&author=" + this.authorKeyword;
                    }
                    else
                    {
                        url += "my_bookmarks";
                        url += "&bookName=" + this.bookNameKeyword;
                        url += "&chapterName=" + this.chapterNameKeyword;
                    }
                        
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
                },
                // 跳转到阅读页
                toRead: function(bookId, chapter)
                {
                    window.open("./read.html?bookId=" + bookId + "&chapter=" + chapter);
                },
                // 删除书签
                deleteBookmark: function(chapterId)
                {
                    if (confirm("您确定要删除该书签吗？"))
                    {
                        request(REMOVE_BOOKMARK_URL, { chapterId: chapterId })
                            .then(() =>
                            {
                                location.reload();
                            });
                    }
                },
                // 搜索评论
                searchComment: function()
                {
                    this.jumpTo(1);
                },
                // 搜索收藏
                searchFavoirte: function()
                {
                    this.jumpTo(1);
                },
                // 搜索书签
                searchBookmark: function()
                {
                    this.jumpTo(1);
                },
                //时间排序选项改变
                changeTimeOrder: function()
                {
                    this.jumpTo(1);
                }
            },
            mounted: function()
            {
                // 从url获取标签页
                let tabParam = getUrlParameter("tab");
                if (tabParam === "my_comments")
                {
                    this.selectedTabIndex = 0;
                    this.bookNameKeyword = getUrlParameter("bookName");
                    if (this.bookNameKeyword === null) this.bookNameKeyword = "";
                    this.commentContentKeyword = getUrlParameter("commentContent");
                    if (this.commentContentKeyword === null) this.commentContentKeyword = "";
                    this.timeOrder = getUrlParameter("timeOrder");
                    if (this.timeOrder === null) this.timeOrder = "按时间降序排序";
                }
                else if (tabParam === "my_favorites")
                {
                    this.selectedTabIndex = 1;
                    this.bookNameKeyword = getUrlParameter("bookName");
                    if (this.bookNameKeyword === null) this.bookNameKeyword = "";
                    this.authorKeyword = getUrlParameter("author");
                    if (this.authorKeyword === null) this.authorKeyword = "";
                }
                else
                {
                    this.selectedTabIndex = 2;
                    this.bookNameKeyword = getUrlParameter("bookName");
                    if (this.bookNameKeyword === null) this.bookNameKeyword = "";
                    this.chapterNameKeyword = getUrlParameter("chapterName");
                    if (this.chapterNameKeyword === null) this.chapterNameKeyword = "";
                }

                // 从url获取页码
                this.currentPage = Number(getUrlParameter("currentPage"));

                // 获取当前用户信息
                request(GET_CURRENT_USER_URL, {})
                    .then(user =>
                    {
                        app.user = user;
                    })
                    .catch(() =>
                    {
                        // 若没有登陆，则跳转到登录页
                        location.href = "./login.html";
                    });

                // 当前处于“我的评论”标签页
                if (this.selectedTabIndex === 0)
                {
                    // 获取当前用户所有评论
                    request(COMMENT_QUERY_OF_USER_URL, 
                        { 
                            pageSize: 10, 
                            currentPage: this.currentPage, 
                            bookName: this.bookNameKeyword, 
                            commentContent: this.commentContentKeyword,
                            isDesc: this.timeOrder === "按时间升序排序" ? false : true
                        })
                        .then(pageBean =>
                        {
                            app.commentsAndBooks = pageBean.data;
                            app.totalPage = pageBean.totalPage;
                            app.totalCount = pageBean.totalCount;
                            app.pagePreview = pageBean.pagePreview;
                        });
                }
                // 当前处于“我的收藏”标签页
                else if (this.selectedTabIndex === 1)
                {
                    // 获取当前用户所有收藏
                    request(FAVORITE_QUERY_URL, 
                        { 
                            pageSize: 10, 
                            currentPage: this.currentPage,
                            bookName: this.bookNameKeyword, 
                            author: this.authorKeyword
                        })
                        .then(pageBean =>
                        {
                            app.favoritesAndBooks = pageBean.data;
                            app.totalPage = pageBean.totalPage;
                            app.totalCount = pageBean.totalCount;
                            app.pagePreview = pageBean.pagePreview;
                        });
                }
                // 当前处于“我的书签”标签页
                else
                {
                    // 获取当前用户的所有书签
                    request(BOOKMARK_QUERY_URL, 
                        { 
                            pageSize: 10,
                            currentPage: this.currentPage,
                            bookName: this.bookNameKeyword, 
                            chapterName: this.chapterNameKeyword
                        })
                        .then(pageBean =>
                        {
                            app.bookmarksAndBooksAndChapters = pageBean.data;
                            app.totalPage = pageBean.totalPage;
                            app.totalCount = pageBean.totalCount;
                            app.pagePreview = pageBean.pagePreview;
                        });
                }
            }
        }
    );
});