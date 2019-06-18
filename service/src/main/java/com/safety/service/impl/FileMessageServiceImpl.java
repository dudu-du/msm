package com.safety.service.impl;

import com.safety.entity.FileMessage;
import com.safety.excel.util.StringUtils;
import com.safety.exception.ProgramException;
import com.safety.mapper.FileMessageMapper;
import com.safety.service.IFileMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 信息收集表 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-25
 */
@Service
public class FileMessageServiceImpl extends ServiceImpl<FileMessageMapper, FileMessage> implements IFileMessageService {
    @Value("${my.upload.imgPath}")
    private String imgPath;

    @Override
    public boolean uploadFile(List<MultipartFile> fileList, String userId, String orgId,int type) throws Exception{
        if(fileList.isEmpty()){
            throw new ProgramException("文件不能为空");
        }
        BufferedOutputStream stream = null;
        for (MultipartFile f : fileList) {
            if (f.isEmpty()) {
                continue;
            }
            byte[] b = f.getBytes();
            String fileName = f.getOriginalFilename();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            if (!new File(imgPath).exists()){
                new File(imgPath).mkdirs();
            }
            String path = imgPath + java.io.File.separator + LocalDateTime.now().format(df)+fileName;
            stream = new BufferedOutputStream(new FileOutputStream(new java.io.File(path)));
            stream.write(b);
            stream.close();
            FileMessage fileMessage = new FileMessage();
            fileMessage.setId(UUIDUtil.getUUID());
            fileMessage.setName(fileName);
            fileMessage.setPath(path);
            fileMessage.setViewPath("/file/"+fileName);
            fileMessage.setOrgFk(orgId);
            fileMessage.setType(type);
            fileMessage.setCreatePersonFk(userId);
            fileMessage.setCreateTime(LocalDateTime.now());
            fileMessage.setModifyTime(LocalDateTime.now());
            baseMapper.insert(fileMessage);
        }
        return true;
    }

    @Override
    public List<FileMessage> getFileMessageList(int type){
        return baseMapper.selectFileMessage(type);
    }

    @Override
    public boolean deleteFile(String id) throws Exception{
        if(StringUtils.isEmpty(id)){
            throw new ProgramException("参数错误");
        }
        FileMessage fileMessage = baseMapper.selectById(id);
        baseMapper.deleteById(fileMessage.getId());
        if(fileMessage == null){
            throw new ProgramException("文件不存在");
        }
        File file = new File(fileMessage.getPath());
        file.delete();
        return true;
    }
}
