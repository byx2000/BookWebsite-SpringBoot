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
            // 获取评分总排名
            queryBooks(
                {
                    orderBy: "score",
                    pageSize: 5,
                    currentPage: 1
                },
                function(pageBean)
                {
                    app.maxScoreRanks.push(pageBean.data);
                }
            );   
            
            // 获取热度总排名
            queryBooks(
                {
                    orderBy: "heat",
                    pageSize: 5,
                    currentPage: 1
                },
                function(pageBean)
                {
                    app.maxHeatRanks.push(pageBean.data);
                }
            );  
            
            // 获取更新时间总排名
            queryBooks(
                {
                    orderBy: "updateTime",
                    pageSize: 5,
                    currentPage: 1
                },
                function(pageBean)
                {
                    app.latestUpdateRanks.push(pageBean.data);
                }
            );  

            // 获取所有类别
            queryCategories({},
                function (categories)
                {
                    //alert(JSON.stringify(categories));
                    for (let category of categories)
                    {
                        app.rankNames.push(category.name);
                        // 获取当前类别的排行榜
                        queryBooks(
                            {
                                categoryId: category.id,
                                orderBy: "score",
                                pageSize: 5,
                                currentPage: 1
                            },
                            function(pageBean)
                            {
                                app.maxScoreRanks.push(pageBean.data);
                            }
                        );  

                        queryBooks(
                            {
                                categoryId: category.id,
                                orderBy: "heat",
                                pageSize: 5,
                                currentPage: 1
                            },
                            function(pageBean)
                            {
                                app.maxHeatRanks.push(pageBean.data);
                            }
                        );  

                        queryBooks(
                            {
                                categoryId: category.id,
                                orderBy: "updateTime",
                                pageSize: 5,
                                currentPage: 1
                            },
                            function(pageBean)
                            {
                                app.latestUpdateRanks.push(pageBean.data);
                            }
                        );  
                    }
                }
            );
        }
    });
});