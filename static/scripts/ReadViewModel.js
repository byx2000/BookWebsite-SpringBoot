$(function()
{
    let app = new Vue(
        {
            el: "#content",
            data:
            {
                bookId: 0,
                currentChapter: 1,
                chapterCount: 1,
                book: null,
                chapterData: null,
                isBookContentsShow: false,
                contents: [],
                jumpTo: function(chapter)
                {
                    location.href = "./read.html?bookId=" + this.bookId + "&chapter=" + chapter;
                }
            },
            methods:
            {
                lastChapter: function()
                {
                    if (this.currentChapter > 1)
                    {
                        this.jumpTo(this.currentChapter - 1);
                    }
                },
                nextChapter: function()
                {
                    if (this.currentChapter < this.chapterCount)
                    {
                        this.jumpTo(this.currentChapter + 1);
                    }
                },
                openOrCloseContents: function()
                {
                    this.isBookContentsShow = !this.isBookContentsShow;
                }
            },
            mounted: function()
            {
                this.bookId = Number(getUrlParameter("bookId"));
                this.currentChapter = Number(getUrlParameter("chapter"));

                // 获取章节数量
                getChapterCount(this.bookId,
                    function(cnt)
                    {
                        app.chapterCount = cnt;

                        // 获取章节文本和电子书信息
                        getChapter(app.bookId, app.currentChapter,
                            function(result)
                            {
                                app.chapterData = result[0];
                                app.book = result[1];
                            }
                        );
                    }
                );

                // 获取目录
                getContents(this.bookId,
                    function(contents)
                    {
                        app.contents = contents;
                    }
                );
            }
        }
    );
});