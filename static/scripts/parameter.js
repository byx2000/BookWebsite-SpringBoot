function getUrlParameter(key)
{
    let paramsStr = window.location.search;
    let params = new URLSearchParams(paramsStr);
    return params.get(key);
}