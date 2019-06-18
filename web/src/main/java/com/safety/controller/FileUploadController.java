package com.safety.controller;

import com.safety.entity.FileMessage;
import com.safety.service.impl.FileMessageServiceImpl;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/file/fileUpload")
public class FileUploadController extends BaseController {

    @Autowired
    private FileMessageServiceImpl fileMessageService;
    @PostMapping("/upload")
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String filePath = "C:/template/";
        if (!new File(filePath).exists()) {
            new File(filePath).mkdirs();
        }
        String realFileName = filePath + System.currentTimeMillis() + fileName;
        File dest = new File(realFileName);
        try {
            file.transferTo(dest);
            return renderSuccess("上传文件成功", realFileName);
        } catch (IOException e) {
            return renderError("上传文件失败");
        }
    }

    @RequestMapping("/downloadFile")
    private String downloadFile(HttpServletResponse response, String fileId) {
        FileMessage fileMessage = fileMessageService.getById(fileId);
        String downloadFilePath = fileMessage.getPath();//被下载的文件在服务器中的路径,
        String fileName = fileMessage.getName();//被下载文件的名称
        File file = new File(downloadFilePath);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开            
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }
}
