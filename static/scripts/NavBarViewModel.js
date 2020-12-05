$(function()
{
    let app = new Vue(
    {
        el: "#nav",
        data: 
        {
            tabs: ["首页", "分类", "排行", "关于"],
            links: [
                "./index.html", 
                "./category.html?categoryIndex=0&updateTimeIndex=0&orderByIndex=0&currentPage=1", 
                "./rank.html", 
                "./about.html"],
            selectedTabIndex: -1,
            keyword: "",
            user: null
        },
        methods: 
        {
            // 点击标签
            onTabClick: function(index)
            {
                this.selectedTabIndex = index;
            },
            // 点击搜索按钮
            search: function()
            {
                if (this.keyword !== "")
                {
                    location.href = "./search_result.html?keyword=" + this.keyword + "&currentPage=1";
                }
            },
            // 注销
            logout: function()
            {
                logout(function()
                {
                    location.href = "./login.html";
                },
                function(errMsg)
                {
                    alert(errMsg);
                });
            }
        },
        mounted: function()
        {
            // 从url获取搜索词
            let keyword = getUrlParameter("keyword");
            if (keyword !== null)
            {
                this.keyword = keyword;
            }

            // 根据url确定当前被选中的选项卡
            let currentPage = location.pathname.split("/")[2];
            if (currentPage == "index.html")
                this.selectedTabIndex = 0;
            else if (currentPage == "category.html")
                this.selectedTabIndex = 1;
            else if (currentPage == "rank.html")
                this.selectedTabIndex = 2;
            else if (currentPage == "about.html")
                this.selectedTabIndex = 3;

            // 获取当前登录用户
            getCuurentUser(
            function(user) // 已登录
            {
                app.user = user;
            },
            function(errMsg) // 未登录
            {
                
            });
        }
    });
});