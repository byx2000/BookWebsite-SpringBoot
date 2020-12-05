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
                        // 登录成功，跳转到登陆前的页面
                        if (document.referrer.indexOf("register.html") === -1)
                            window.location.href = document.referrer;
                        else
                            window.location.href = "./index.html";
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