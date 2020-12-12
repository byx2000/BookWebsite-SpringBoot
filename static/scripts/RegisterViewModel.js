$(function()
{
    let app = new Vue(
        {
            el: "#content",
            data:
            {
                username: "",
                password: "",
                nickname: "",
                avatar: null
            },
            methods:
            {
                fileChange: function(e)
                {
                    this.avatar = e.target.files[0];
                },
                register: function()
                {
                    if (this.username.trim() === "")
                    {
                        alert("用户名不能为空");
                        return;
                    }

                    if (this.password.trim() === "")
                    {
                        alert("密码不能为空");
                        return;
                    }

                    if (this.nickname.trim() === "")
                    {
                        alert("昵称不能为空");
                        return;
                    }

                    if (this.avatar === null)
                    {
                        alert("请选择头像");
                        return;
                    }

                    let formData = new FormData();
                    formData.append("username", this.username);
                    formData.append("password", this.password);
                    formData.append("nickname", this.nickname);
                    formData.append("avatar", this.avatar);
                    request(REGISTER_URL, formData, false, false)
                        .then(() =>
                        {
                            // 注册成功后跳转到登录页面
                            location.href = "./login.html"
                        })
                        .catch(error =>
                        {
                            alert(error);
                        });
                }
            },
            mounted: function()
            {
                
            }
        }
    );
});