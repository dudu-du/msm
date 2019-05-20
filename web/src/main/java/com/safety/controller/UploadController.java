package com.safety.controller;

import com.safety.exception.ProgramException;
import com.safety.service.IUploadService;
import com.safety.tools.BASE64DecodedMultipartFile;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 图片管理
 */
@Controller
@Slf4j
@RequestMapping(value = "View")
public class UploadController extends BaseController {

    @Autowired
    private IUploadService uploadService;

//    /**
//     * 上传图片(通用,该方法不要用,如需要图片上传调接口)
//     *
//     * @param file         文件
//     * @param id           图片id
//     * @param folderSecond 设置二级图片路径(例client/)
//     * @return
//     */
//    @RequestMapping(value = "picture", method = RequestMethod.POST)
//    @ResponseBody
//    @CrossOrigin
//    public JsonResult uploadPicture(MultipartFile file, String id, String folderSecond) {
//        try {
//            String pictureUrl = uploadService.uploadPicture(file, id, folderSecond);
//            log.info("上传后的文件地址:" + pictureUrl);
//            return renderSuccess("上传成功", pictureUrl);
//        } catch (ProgramException p) {
//            log.error("上传图片失败." + p.getMessage());
//            return renderError(p.getMessage());
//        } catch (Exception e) {
//            log.error("上传图片失败." + e.getMessage());
//            return renderError("上传失败");
//        }
//    }
//
//    /**
//     * 上传图片(base64,该方法不要用,如需要图片上传调接口)
//     *
//     * @param uploadPicture64 base64文件流
//     * @param folderSecond    图片在OSS上的地址 例client/
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "pictureBase64", method = RequestMethod.POST)
//    @ResponseBody
//    @CrossOrigin
//    public JsonResult uploadPictureBase64(String uploadPicture64, String id, String folderSecond) {
//        try {
//            MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(uploadPicture64);
//            String pictureUrl = uploadService.uploadPicture(file, id, folderSecond);
//            log.info("上传后的文件地址:" + pictureUrl);
//            return renderSuccess("上传成功", pictureUrl);
//        } catch (ProgramException p) {
//            log.error("上传图片失败." + p.getMessage());
//            return renderError(p.getMessage());
//        } catch (Exception e) {
//            log.error("上传图片失败." + e.getMessage());
//            return renderError("上传失败");
//        }
//    }


    /**
     * 上传文件
     *
     * @param file 文件(单文件上传)
     * @param path 路径(例学生相册:student/机构id/)
     * @return
     */
    @RequestMapping(value = "file", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult uploadFile(MultipartFile file, String path) {
        try {
            String pictureUrl = uploadService.uploadFile(file, path);
            log.info("上传后的文件地址:" + pictureUrl);
            return renderSuccess("上传文件成功", pictureUrl);
        } catch (ProgramException p) {
            log.error("上传文件片失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("上传文件片失败." + e.getMessage());
            return renderError("上传文件片失败");
        }
    }


    /**
     * 上传文件(base64)
     *
     * @param fileBase64 base64文件流(单文件上传)
     * @param path       路径(例学生相册:student/机构id/)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "fileBase64", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult uploadFileBase64(String fileBase64, String path) {
        try {
            MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(fileBase64);
            String pictureUrl = uploadService.uploadFile(file, path);
            log.info("上传后的文件地址:" + pictureUrl);
            return renderSuccess("上传文件成功", pictureUrl);
        } catch (ProgramException p) {
            log.error("上传文件失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("上传文件失败." + e.getMessage());
            return renderError("上传文件失败");
        }
    }


}
