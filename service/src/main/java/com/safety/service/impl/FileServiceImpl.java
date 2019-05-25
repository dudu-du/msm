package com.safety.service.impl;

import com.safety.entity.File;
import com.safety.mapper.FileMapper;
import com.safety.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 信息收集表 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-25
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
