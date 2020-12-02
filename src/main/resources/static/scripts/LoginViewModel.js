$(function()
{
    let app = new Vue(
    {
        el: "#login",
        data:
        {
            username: "",
            password: ""
        },
        methods:
        {
            login: function()
            {
                if (this.username === "" || this.password === "")
                {
                    alert("用户名或密码不能为空！");
                }
                else
                {
                    login(this.username, this.password,
                    function(user)
                    {
                        // 登陆成功，跳转到首页
                        //location.href = "./index.html";

                        // 登录成功，跳转到登陆前的页面
                        window.location.href = document.referrer;
                    },
                    function(errMsg)
                    {
                        alert("登录失败：" + errMsg);
                    });
                }
            }
        }
    });
});