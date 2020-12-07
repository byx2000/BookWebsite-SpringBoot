$(function()
{
    let app = new Vue(
        {
            el: "#content",
            data:
            {
                bookId: 0,
                chapter: 1,
                chapterCount: 1,
                book: null,
                text: null,
                jumpTo: function(chapter)
                {
                    location.href = "./read.html?bookId=" + this.bookId + "&chapter=" + chapter;
                }
            },
            methods:
            {
                lastChapter: function()
                {
                    if (this.chapter > 1)
                    {
                        this.jumpTo(this.chapter - 1);
                    }
                },
                nextChapter: function()
                {
                    if (this.chapter < this.chapterCount)
                    {
                        this.jumpTo(this.chapter + 1);
                    }
                }
            },
            mounted: function()
            {
                this.bookId = Number(getUrlParameter("bookId"));
                this.chapter = Number(getUrlParameter("chapter"));

                // 获取章节数量
                getChapterCount(this.bookId,
                    function(cnt)
                    {
                        app.chapterCount = cnt;

                        // 获取章节文本和电子书信息
                        getChapter(app.bookId, app.chapter,
                            function(result)
                            {
                                app.text = result[0];
                                app.book = result[1];
                            }
                        );
                    }
                );

            }
        }
    );
});