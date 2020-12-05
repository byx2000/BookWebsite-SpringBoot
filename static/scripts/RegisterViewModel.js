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
                    //alert(JSON.stringify(e));
                    //alert(e.target.files[0]);
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

                    //alert(this.username + "\n" + this.password + "\n" + this.nickname + "\n" + this.avatar);
                    register(this.username, this.password, this.nickname, this.avatar,
                        function()
                        {
                            location.href = "./login.html"
                        }
                    );
                }
            },
            mounted: function()
            {
                
            }
        }
    );
});