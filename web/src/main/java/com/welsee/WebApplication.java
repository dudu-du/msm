package com.welsee;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.welsee.tools.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.HttpPutFormContentFilter;

@SpringBootApplication
@MapperScan("com.welsee.mapper")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableCaching(proxyTargetClass = true)
public class WebApplication {

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    @Bean
    public SpringUtil springUtil(){
        return new SpringUtil();
    }
    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
