package com.safety.shiro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Value("${my.upload.imgPath}")
    private String path;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String staticMapping="/image/**";
        String localDirectory = "file:"+path;
        registry.addResourceHandler(staticMapping).addResourceLocations(localDirectory);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}