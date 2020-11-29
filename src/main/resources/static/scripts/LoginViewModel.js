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
                        location.href = "./index.html";
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