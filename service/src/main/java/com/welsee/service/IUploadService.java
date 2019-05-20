package com.welsee.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传接口
 */
public interface IUploadService {

    /**
     * 删除oss上的图书
     *
     * @param id 图书id
     * @return
     */
    boolean deleteOssFile(String id) throws Exception;

    /**
     * 上传图片
     *
     * @param file 文件
     * @param path 路径
     * @return
     * @throws Exception
     */
    String uploadFile(MultipartFile file, String path) throws Exception;

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 路径
     * @return
     * @throws Exception
     */
    String uploadPublicFile(MultipartFile file, String path) throws Exception;
}
