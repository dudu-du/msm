package com.welsee.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class Config {
    public static String webVersion;

    public static String pictureOss;

    public static String weatherPath;

    public static String videoUrl;

    public static String videoClientId;


    @Value("${webVersion}")
    public void setWebVersion(String webVersion) {
        Config.webVersion = webVersion;
    }


    @Value("${pictureOss}")
    public void setWebPicture(String pictureOss){
        Config.pictureOss  =pictureOss;
    }

    @Value("${weatherPath}")
    public void setWeatherPath(String weatherPath){
        Config.weatherPath  =weatherPath;
    }

    @Value("${videoUrl}")
    public void setVideoUrl(String videoUrl) {
        Config.videoUrl = videoUrl;
    }

    @Value("${videoClientId}")
    public void setVideoClientId(String videoClientId) {
        Config.videoClientId = videoClientId;
    }

    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("50MB"); //KB,MB
        /// 设置总上传数据总
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }
}
