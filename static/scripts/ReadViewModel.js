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
            }
        }
    );
});