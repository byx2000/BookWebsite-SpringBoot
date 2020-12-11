// 发起请求
function request1(url, parameters, success, fail = e => alert(e))
{
    $.ajax(
        {
            url: url,
            data: parameters,
            success: function(response)
            {
                if (response.flag) success(response.data);
                else fail(response.errMsg);
            },
            error: function()
            {
                alert("网络错误");
            }
        }
    );
}

// 查询分类
function queryCategories(conditions, success, fail)
{
    request1("category/query", conditions, success, fail);
}

// 查询电子书
function queryBooks(conditions, success, fail)
{
    request1("book/query", conditions, success, fail);
}

// 登录
function login(username, password, success, fail)
{
    request1("user/login", 
    { 
        username: username,
        password: password
    }, 
    success, fail);
}

// 获取当前登录用户
function getCuurentUser(success, fail)
{
    request1("user/current", {}, success, fail);
}

// 注销
function logout(success, fail)
{
    request1("user/logout", {}, success, fail);
}

// 查询用户
function queryUsers(conditions, success, fail)
{
    request1("user/query", conditions, success, fail);
}

// 查询评论
function queryComments(conditions, success, fail)
{
    request1("comment/query", conditions, success, fail);
}

// 发表评论
function publishComment(bookId, content, success, fail)
{
    request1("comment/publish", { bookId: bookId, content: content }, success, fail);
}

// 删除评论
function deleteComment(commentId, success, fail)
{
    request1("comment/delete", { commentId: commentId }, success, fail);
}

// 查询收藏记录
function queryFavorites(conditions, success, fail)
{
    request1("favorite/query", conditions, success, fail);
}

// 是否收藏
function isFavorite(bookId, success, fail)
{
    request1("favorite/isFavorite", { bookId: bookId }, success, fail);
}

// 添加收藏记录
function addFavorite(bookId, success, fail)
{
    request1("favorite/add", { bookId: bookId }, success, fail);
}

// 取消收藏
function cancelFavorite(bookId, success, fail)
{
    request1("favorite/cancel", { bookId: bookId }, success, fail);
}

// 点赞
function evaluate(bookId, cmd, success, fail)
{
    request1("evaluate", { bookId: bookId, cmd: cmd }, success, fail);
}

// 是否喜欢
function isLike(bookId, success, fail)
{
    request1("evaluate/isLike", { bookId: bookId }, success, fail);
}

// 是否不喜欢
function isDislike(bookId, success, fail)
{
    request1("evaluate/isDislike", { bookId: bookId }, success, fail);
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
    request1("chapter/count", { bookId: bookId }, success, fail);
}

// 获取章节
function getChapter(bookId, chapter, success, fail)
{
    request1("chapter/data", { bookId: bookId, chapter: chapter }, success, fail);
}

// 获取目录
function getContents(bookId, success, fail)
{
    request1("chapter/contents", { bookId: bookId }, success, fail);
}

function request(url, params)
{
    return new Promise(
        (resolve, reject) =>
        {
            $.ajax(
                {
                    url: url,
                    data: params,
                    type: "POST",
                    success: function(response)
                    {
                        if (response.flag) resolve(response.data);
                        else reject(response.errMsg)
                    },
                    error: function(error)
                    {
                        reject(error);
                    }
                }
            );
        }
    );
}

var BOOK_QUERY_URL = "book/query";
var CATEGORY_QUERY_URL = "category/query";
var COMMENT_QUERY_URL = "comment/query";
var IS_FAVORITE_URL = "favorite/isFavorite";
var IS_LIKE_URL = "evaluate/isLike";
var IS_DISLIKE_URL = "evaluate/isDislike";
var GET_CURRENT_USER_URL = "user/current";
var EVALUATE_URL = "evaluate";
var FAVORITE_URL = "favorite/add";
var UNFAVORITE_URL = "favorite/cancel";
var PUBLISH_COMMENT_URL = "comment/publish";