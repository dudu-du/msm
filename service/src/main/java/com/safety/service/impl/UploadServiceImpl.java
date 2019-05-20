package com.safety.service.impl;

import com.safety.exception.ProgramException;
import com.safety.service.IUploadService;
import com.safety.tools.AliyunOSSClientUtil;
import com.safety.tools.DateTimeUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片上传实现类
 */
@Service
public class UploadServiceImpl implements IUploadService {


    @Override
    public synchronized String uploadFile(MultipartFile multipartfile, String path) throws Exception {
        if (multipartfile == null || path == null) {
            throw new ProgramException("上传文件参数不合法");
        }
        if (multipartfile.getSize() > 50 * 1024 * 1024) {
            throw new ProgramException("上传图片大小不能超过50M!");
        }

        //设置统一图片后缀名
        String suffixName;

        //获取当前时间的毫秒作为图片的文件名(不带扩展名的文件名)
        String prefixName = DateTimeUtil.getMilliSecondTimestamp();

        //获取图片后缀名,判断如果是png的话就不进行格式转换,因为Thumbnails存在转png->jpg图片变红bug
        String suffixNameOrigin = getExtensionName(multipartfile.getOriginalFilename());

        if ("png".equals(suffixNameOrigin)) {
            suffixName = "png";
        } else if ("jpeg".equals(suffixNameOrigin)) {
            suffixName = "png";
        } else {
            suffixName = "jpg";
        }

        //图片存储文件夹
        String filePath = "web/src/main/resources/";

        //图片在项目中的地址(项目位置+图片名,带后缀名)
        String contextPath = filePath + prefixName + "." + suffixName;
        //图片在项目中的地址(项目位置+图片名,带后缀名)
        File tempFile = new File(contextPath);
        if (!tempFile.exists()) {
            //生成图片文件
            FileUtils.copyInputStreamToFile(multipartfile.getInputStream(), tempFile);
        }

        /*
         * size(width,height) 若图片横比1920小，高比1080小，不变
         * 若图片横比1920小，高比1080大，高缩小到1080，图片比例不变 若图片横比1920大，高比1080小，横缩小到1920，图片比例不变
         * 若图片横比1920大，高比1080大，图片按比例缩小，横为1920或高为1080
         * 图片格式转化为jpg,质量不变
         */
        pictureCompression(multipartfile, suffixName, contextPath);

        //获取压缩后的图片
        File f = new File(contextPath);
        InputStream inputStream = new FileInputStream(f);


        //设置图片存储在oss上的名字
        String fileName = prefixName + "." + suffixName;

        try {
            //上传图片到OSS,返回图路径
            String resultUrl = AliyunOSSClientUtil.uploadImg2Oss(inputStream, path, fileName);

            //将临时文件删除
            f.delete();

            return resultUrl;
        } catch (Exception e) {
            throw new ProgramException("文件上传失败");
        } finally {
            inputStream.close();
        }
    }

    @Override
    public String uploadPublicFile(MultipartFile file, String path) throws Exception {
        if (file == null || path == null) {
            throw new ProgramException("上传文件参数不合法");
        }
        //设置统一文件后缀名
        String suffixName;

        //获取当前时间的毫秒作为文件的文件名(不带扩展名的文件名)
        String prefixName = DateTimeUtil.getMilliSecondTimestamp();

        suffixName = getExtensionName(file.getOriginalFilename());

        //设置图片存储在oss上的名字
        String fileName = prefixName + "." + suffixName;

        InputStream inputStream = file.getInputStream();

        //上传文件到OSS,返回图路径
        String resultUrl = AliyunOSSClientUtil.uploadImg2Oss(inputStream, path, fileName);

        return resultUrl;
    }

    @Override
    public boolean deleteOssFile(String id) throws Exception {
        if (id == null) {
            throw new ProgramException("删除Oss文件参数不合法");
        }
        //设置图书位置的二级文件夹名
        String folderSecond = "wp/";

        //设置三级文件夹名
        String folderThird = id;

        //设置OSS上要删除的的二级文件目录(client/45e233d07c664b93b7bb35331285a8d8)
        String folderPath = folderSecond + folderThird;

        //删除OSS上的文件(文件夹+文件)
        return AliyunOSSClientUtil.deleteFile2Oss(folderPath);
    }

    /**
     * 图片压缩和格式转换
     *
     * @param multipartfile 文件
     * @param suffixName    文件后缀
     * @param contextPath   转换后的图片路径
     * @throws IOException
     */
    private void pictureCompression(MultipartFile multipartfile, String suffixName, String contextPath) throws IOException {
        BufferedImage image = ImageIO.read(multipartfile.getInputStream());

        if (image.getHeight() > 1080 || image.getWidth() > 1920) {
            if (!"png".equals(suffixName)) {
                Thumbnails.of(contextPath).size(1920, 1080).outputQuality(1f).outputFormat("jpg").toFile(contextPath);
            } else {
                Thumbnails.of(contextPath).size(1920, 1080).outputQuality(1f).toFile(contextPath);
            }
        } else {
            if (!"png".equals(suffixName)) {
                Thumbnails.of(contextPath).outputQuality(1f).scale(1f).outputFormat("jpg").toFile(contextPath);
            } else {
                Thumbnails.of(contextPath).outputQuality(1f).scale(1f).toFile(contextPath);
            }
        }
    }

    /**
     * 获取不带扩展名的文件名
     *
     * @param filename 文件名
     * @return
     */
    private static String getFileNameWithoutEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 获取文件扩展名
     *
     * @param filename 文件名
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

}
