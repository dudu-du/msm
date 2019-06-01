package com.safety.controller;

import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/file/fileUpload")
public class FileUploadController  extends BaseController {
    @PostMapping("/upload")
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String filePath = "C:/template/";
        if (!new File(filePath).exists()){
            new File(filePath).mkdirs();
        }
        String realFileName = filePath + System.currentTimeMillis()+fileName;
        File dest = new File(realFileName);
        try {
            file.transferTo(dest);
            return renderSuccess("上传文件成功",realFileName);
        } catch (IOException e) {
            return renderError("上传文件失败");
        }
    }
}
