package com.welsee.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义拦截器
 * @Auther: Tt(yehuawei)
 * @Date: 2018/7/11 14:43
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {  //实现原生拦截器的接口

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        if( uri.equals("/") || uri.equals("base")){
            System.out.println("进入判断");
            String domainName = request.getRequestURL().toString().toLowerCase().replaceAll("http://","");
            if(domainName.endsWith("/"))
            {
                domainName = domainName.substring(0,domainName.length()-1);
            }
            String orgId = "";
//            try {
//                Map<String,Object> schoolMap = schoolService.getSchoolMapBydomainName(domainName);
//                if(!schoolMap.isEmpty()){
//                    orgId = schoolMap.get("org_id").toString();
//                }
//            }
//            catch (Exception e){
//                log.error("域名未匹配到机构信息："+domainName);
//            }
            if(!orgId.equals("")){
                request.setAttribute("orgId",orgId);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
