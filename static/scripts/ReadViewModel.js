$(function()
{
    let app = new Vue(
        {
            el: "#content",
            data:
            {
                bookId: 0,
                currentChapter: 1,
                chapterAndBook: null,
                isBookContentsShow: false,
                contents: [],
                isBookmark: false,
                jumpTo: function(chapter)
                {
                    location.href = "./read.html?bookId=" + this.bookId + "&chapter=" + chapter;
                }
            },
            methods:
            {
                // 上一章
                lastChapter: function()
                {
                    if (this.currentChapter > 1)
                    {
                        this.jumpTo(this.currentChapter - 1);
                    }
                },
                // 下一章
                nextChapter: function()
                {
                    if (this.currentChapter < this.contents.length)
                    {
                        this.jumpTo(this.currentChapter + 1);
                    }
                },
                // 打开或关闭目录
                openOrCloseContents: function()
                {
                    this.isBookContentsShow = !this.isBookContentsShow;

                    // 滚动到当前章节
                    if (this.isBookContentsShow)
                    {
                        setTimeout(() =>
                        {
                            $("#contents_item_" + String(app.currentChapter - 1))[0].scrollIntoView();
                        }, 50);
                    }
                },
                // 添加书签
                addBookmark: function()
                {
                    request(ADD_BOOKMARK_URL, { bookId: this.bookId, chapter: this.currentChapter })
                        .then(() =>
                        {
                            app.isBookmark = true;
                            app.isBookContentsShow = false;
                        });
                },
                // 移除书签
                removeBookmark: function()
                {
                    request(REMOVE_BOOKMARK_URL, { bookId: this.bookId, chapter: this.currentChapter })
                        .then(() =>
                        {
                            app.isBookmark = false;
                            app.isBookContentsShow = false;
                        });
                }
            },
            mounted: function()
            {
                // 从url获取电子书id和当前章节
                this.bookId = Number(getUrlParameter("bookId"));
                this.currentChapter = Number(getUrlParameter("chapter"));

                // 获取章节数据和电子书数据
                request(GET_CHAPTER_URL, { bookId: this.bookId, chapter: this.currentChapter })
                    .then(chapterAndBook =>
                    {
                        app.chapterAndBook = chapterAndBook;
                    });

                // 获取目录
                request(GET_CONTENTS_URL, { bookId: this.bookId })
                    .then(contents =>
                    {
                        app.contents = contents;
                    });

                // 判断当前章节是否已标记书签
                request(IS_BOOKMARK_URL, { bookId: this.bookId, chapter: this.currentChapter })
                    .then(isBookmark =>
                    {
                        app.isBookmark = isBookmark;
                    });
            }
        }
    );
});