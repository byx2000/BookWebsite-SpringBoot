// 将jquery的异步ajax请求改造成promise模式
function request(url, params, processData = true, contentType = "application/x-www-form-urlencoded")
{
    return new Promise(
        (resolve, reject) =>
        {
            $.ajax(
                {
                    url: url,
                    data: params,
                    type: "POST",
                    processData: processData,
                    contentType: contentType,
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

// 后端url
var BOOK_QUERY_URL = "book/query";
var CATEGORY_QUERY_URL = "category/query";
var COMMENT_QUERY_OF_BOOK_URL = "comment/query_of_book";
var COMMENT_QUERY_OF_USER_URL = "comment/query_of_user";
var IS_FAVORITE_URL = "favorite/isFavorite";
var IS_LIKE_URL = "evaluate/isLike";
var IS_DISLIKE_URL = "evaluate/isDislike";
var GET_CURRENT_USER_URL = "user/current";
var EVALUATE_URL = "evaluate";
var FAVORITE_URL = "favorite/add";
var UNFAVORITE_URL = "favorite/cancel";
var PUBLISH_COMMENT_URL = "comment/publish";
var FAVORITE_QUERY_URL = "favorite/query";
var DELETE_COMMENT_URL = "comment/delete";
var LOGOUT_URL = "user/logout";
var LOGIN_URL = "user/login";
var REGISTER_URL = "user/register";
var GET_CHAPTER_URL = "chapter/data";
var GET_CONTENTS_URL = "chapter/contents";
var ADD_BOOKMARK_URL = "bookmark/add";
var REMOVE_BOOKMARK_URL = "bookmark/remove";
var IS_BOOKMARK_URL = "bookmark/is_bookmark";
var BOOKMARK_QUERY_URL = "bookmark/query";
var BOOK_SEARCH_PREDICT_URL = "book/search_predict";