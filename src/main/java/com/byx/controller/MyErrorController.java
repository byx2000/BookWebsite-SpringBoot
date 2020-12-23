package com.byx.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * 转发到自定义Http错误页面
 */
@Controller
public class MyErrorController implements ErrorController
{
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request)
    {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null)
        {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirect:/error/404.html";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
            {
                return "redirect:/error/500.html";
            }
        }
        return "redirect:/error/500.html";
    }

    @Override
    public String getErrorPath()
    {
        return null;
    }
}
