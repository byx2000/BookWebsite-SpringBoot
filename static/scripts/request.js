// 发起请求
function request(url, parameters, success, fail = e => alert(e))
{
    $.post(url, parameters, 
    function(response)
    {
        if (response.flag) success(response.data);
        else fail(response.errMsg);
    });
}

// 查询分类
function queryCategories(conditions, success, fail)
{
    request("category/query", conditions, success, fail);
}

// 查询电子书
function queryBooks(conditions, success, fail)
{
    request("book/query", conditions, success, fail);
}

// 登录
function login(username, password, success, fail)
{
    request("user/login", 
    { 
        username: username,
        password: password
    }, 
    success, fail);
}

// 获取当前登录用户
function getCuurentUser(success, fail)
{
    request("user/current", {}, success, fail);
}

// 注销
function logout(success, fail)
{
    request("user/logout", {}, success, fail);
}

// 查询用户
function queryUsers(conditions, success, fail)
{
    request("user/query", conditions, success, fail);
}

// 查询评论
function queryComments(conditions, success, fail)
{
    request("comment/query", conditions, success, fail);
}

// 添加评论
function saveComment(bookId, content, success, fail)
{
    request("comment/save", { bookId: bookId, content: content }, success, fail);
}

// 删除评论
function deleteComment(commentId, success, fail)
{
    request("comment/delete", { commentId: commentId }, success, fail);
}

// 查询收藏记录
function queryFavorites(conditions, success, fail)
{
    request("favorite/query", conditions, success, fail);
}

// 添加收藏记录
function addFavorite(bookId, success, fail)
{
    request("favorite/add", { bookId: bookId }, success, fail);
}

// 取消收藏
function cancelFavorite(favoriteId, success, fail)
{
    request("favorite/cancel", { favoriteId: favoriteId }, success, fail);
}

// 点赞
function evaluate(bookId, cmd, success, fail)
{
    request("evaluate", { bookId: bookId, cmd: cmd }, success, fail);
}

// 是否喜欢
function isLike(bookId, success, fail)
{
    request("evaluate/isLike", { bookId: bookId }, success, fail);
}

// 是否不喜欢
function isDislike(bookId, success, fail)
{
    request("evaluate/isDislike", { bookId: bookId }, success, fail);
}

// 注册
function register(username, password, nickname, avatar, success, fail = e => alert(e))
{
    let formData = new FormData();
    formData.append("username", username);
    formData.append("password", password);
    formData.append("nickname", nickname);
    formData.append("avatar", avatar);
    $.ajax(
        {
            url: "user/register", 
            type: 'POST',
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            success: function(response)
            {
                if (response.flag) success(response.data);
                else fail(response.errMsg);
            }
        }
    );
}

// 获取章节数
function getChapterCount(bookId, success, fail)
{
    request("chapter/count", { bookId: bookId }, success, fail);
}

// 获取章节
function getChapter(bookId, chapter, success, fail)
{
    request("chapter/data", { bookId: bookId, chapter: chapter }, success, fail);
}