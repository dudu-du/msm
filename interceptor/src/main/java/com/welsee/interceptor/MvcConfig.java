package com.welsee.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 *
 *
 * @Auther: wuzhenzhen
 * @Date: 2019/1/10
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
//    以下WebMvcConfigurerAdapter 比较常用的重写接口
//    /** 解决跨域问题 **/
//    public void addCorsMappings(CorsRegistry registry) ;
//    /** 添加拦截器 **/
//    void addInterceptors(InterceptorRegistry registry);
//    /** 这里配置视图解析器 **/
//    void configureViewResolvers(ViewResolverRegistry registry);
//    /** 配置内容裁决的一些选项 **/
//    void configureContentNegotiation(ContentNegotiationConfigurer configurer);
//    /** 视图跳转控制器 **/
//    void addViewControllers(ViewControllerRegistry registry);
//    /** 静态资源处理 **/
//    void addResourceHandlers(ResourceHandlerRegistry registry);
//    /** 默认静态资源处理器 **/
//    void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     *
     * 功能描述:
     *  配置静态资源,避免静态资源请求被拦截
     * @auther: Tt(yehuawei)
     * @date:
     * @param:
     * @return:
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        该配置无效，因为访问js文件时访问地址形式为http://localhost:8080/node_modules/**.js,
//        而不是http://localhost:8080/static/node_modules/**.js
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/node_modules/**")
                .addResourceLocations("classpath:/static/node_modules/");
        registry.addResourceHandler("/Public/**")
                .addResourceLocations("classpath:/static/Public/");
        registry.addResourceHandler("/401.html")
                .addResourceLocations("classpath:/static/401.html");
        registry.addResourceHandler("/404.html")
                .addResourceLocations("classpath:/static/404.html");
        registry.addResourceHandler("/500.html")
                .addResourceLocations("classpath:/static/500.html");
        super.addResourceHandlers(registry);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                //addPathPatterns 用于添加拦截规则
                .addPathPatterns("/**")
                //excludePathPatterns 用于排除拦截
                //注意content-path是不用填写的
                //api
                .excludePathPatterns("/Public/**")
                .excludePathPatterns("/oauth2/**")
                .excludePathPatterns("/View/**")
                .excludePathPatterns("/captcha")
                .excludePathPatterns("/node_modules/**")
                .excludePathPatterns("/401.html")
                .excludePathPatterns("/404.html")
                .excludePathPatterns("/500.html");
        super.addInterceptors(registry);
    }
}