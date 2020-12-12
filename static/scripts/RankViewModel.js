$(function()
{
    let app = new Vue(
    {
        el: "#rank",
        data:
        {
            rankNames: ["综合"],
            maxScoreRanks: [],
            maxHeatRanks: [],
            latestUpdateRanks: []
        },
        methods:
        {

        },
        mounted: function()
        {
            // 获取所有类别
            request(CATEGORY_QUERY_URL, {})
                .then(categories =>
                {
                    app.maxScoreRanks = new Array(categories.length + 1);
                    app.maxHeatRanks = new Array(categories.length + 1);
                    app.latestUpdateRanks = new Array(categories.length + 1);

                    // 获取评分总排名
                    request(BOOK_QUERY_URL, { orderBy: "score", limit: 5 })
                        .then(books =>
                        {
                            app.maxScoreRanks.splice(0, 1, books);
                        });

                    // 获取热度总排名
                    request(BOOK_QUERY_URL, { orderBy: "heat", limit: 5 })
                        .then(books =>
                        {
                            app.maxHeatRanks.splice(0, 1, books);
                        });

                    // 获取更新时间总排名
                    request(BOOK_QUERY_URL, { orderBy: "updateTime", limit: 5 })
                        .then(books =>
                        {
                            app.latestUpdateRanks.splice(0, 1, books);
                        });

                    // 获取每个类别的排行榜
                    for (let i = 0; i < categories.length; ++i)
                    {
                        // 添加类别名
                        app.rankNames.push(categories[i].name);

                        // 评分排行榜
                        request(BOOK_QUERY_URL, { categoryId: categories[i].id, orderBy: "score", limit: 5 })
                            .then(books =>
                            {
                                app.maxScoreRanks.splice(i + 1, 1, books);
                            });

                        // 热度排行榜
                        request(BOOK_QUERY_URL, { categoryId: categories[i].id, orderBy: "heat", limit: 5 })
                            .then(books =>
                            {
                                app.maxHeatRanks.splice(i + 1, 1, books);
                            });

                        // 更新时间排行榜
                        request(BOOK_QUERY_URL, { categoryId: categories[i].id, orderBy: "updateTime", limit: 5 })
                            .then(books =>
                            {
                                app.latestUpdateRanks.splice(i + 1, 1, books);
                            });
                    }
                });
        }
    });
});