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
                    return;
                }
                request(LOGIN_URL, { username: this.username, password: this.password })
                    .then(() =>
                    {
                        // 登录成功，跳转到登陆前的页面
                        if (document.referrer.indexOf("register.html") === -1)
                            window.location.href = document.referrer;
                        else
                            window.location.href = "./index.html";
                    })
                    .catch(error =>
                    {
                        alert(error);
                    });
            }
        }
    });
});