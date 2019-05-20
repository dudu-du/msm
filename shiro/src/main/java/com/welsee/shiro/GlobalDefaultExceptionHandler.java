package com.welsee.shiro;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
  * 1、新建一个Class,这里取名为GlobalDefaultExceptionHandler
  * 2、在class上添加注解，@ControllerAdvice;
  * 3、在class中添加一个方法
  * 4、在方法上添加@ExcetionHandler拦截相应的异常信息；
  * 5、如果返回的是View -- 方法的返回值是ModelAndView;
  * 6、如果返回的是String或者是Json数据，那么需要在方法上添加@ResponseBody注解.
  * 
  * 
  * @author gc
  * @version v.0.1
  * @date 2018年8月18日
  */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String defaultAuthorizedExceptionHandler(HttpServletRequest request, HttpServletResponse response,Exception e) {
        return defaultException(request,response);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public String defaultAuthenticatedExceptionHandler(HttpServletRequest request, HttpServletResponse response,Exception e) {
        return defaultException(request,response);
    }

    private String defaultException(HttpServletRequest request, HttpServletResponse response){
        try {
            if (isAjax(request)) {
                onLoginFail(response);
                return "";
            }
            else {
                response.sendRedirect("/notlogin");
            }
        }
        catch (Exception ex){

        }
        return "您没有访问权限";
    }

    public boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void onLoginFail(HttpServletResponse response) throws IOException {
        response.setHeader("sessionstatus", "timeout");
        response.setHeader("basePath", "/notlogin");
    }

}