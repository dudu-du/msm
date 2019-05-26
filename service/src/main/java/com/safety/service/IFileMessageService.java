package com.safety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.entity.FileMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 信息收集表 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-25
 */
public interface IFileMessageService extends IService<FileMessage> {

    /**
     * 保存文件
     * @param fileList
     * @param userId
     * @param orgId
     * @throws Exception
     */
    boolean uploadFile(List<MultipartFile> fileList, String userId, String orgId,int type) throws Exception;
}
