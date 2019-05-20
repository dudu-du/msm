package com.welsee.tools;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixinyu
 * @class:OSSClientConstants
 * @descript:阿里云注册用户基本常量
 * @date:2018年11月06日 下午5:52:34
 */
@Configuration
public class OSSClientConstants {
    //阿里云API的外网域名
    public static String ENDPOINT ;
    //阿里云API的密钥Access Key ID
    public static String ACCESS_KEY_ID ;
    //阿里云API的密钥Access Key Secret
    public static String ACCESS_KEY_SECRET ;
    //阿里云API的bucket名称
    public static String BUCKET_NAME;
    //阿里云API的文件夹名称
    public static String FOLDER ;
    //阿里云OSS根路径
    public static String PICTURE_OSS;

    @Value("${OSS_ENDPOINT}")
    public void setENDPOINT(String ENDPOINT) {
        OSSClientConstants.ENDPOINT = ENDPOINT;
    }

    @Value("${OSS_ACCESS_KEY_ID}")
    public void setAccessKeyId(String accessKeyId) {
        OSSClientConstants.ACCESS_KEY_ID = accessKeyId;
    }

    @Value("${OSS_ACCESS_KEY_SECRET}")
    public void setAccessKeySecret(String accessKeySecret) {
        OSSClientConstants.ACCESS_KEY_SECRET = accessKeySecret;
    }


    @Value("${OSS_BUCKET_NAME}")
    public void setBucketName(String bucketName) {
        BUCKET_NAME = bucketName;
    }

    @Value("${OSS_FOLDER}")
    public void setFOLDER(String FOLDER) {
        OSSClientConstants.FOLDER = FOLDER;
    }

    @Value("${pictureOss}")
    public void setPICTURE_OSS(String PICTURE_OSS) {
        OSSClientConstants.PICTURE_OSS = PICTURE_OSS;
    }
}
