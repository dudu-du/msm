package com.safety.controller;


import com.safety.entity.Person;
import com.safety.exception.ProgramException;
import com.safety.service.IFileMessageService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 信息收集表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-25
 */
@Controller
@RequestMapping("/safety/file")
@Slf4j
public class FileMessageController extends BaseController {
    @Autowired
    private IFileMessageService iFileMessageService;

    @RequestMapping(value = "/fileInsideView",method = RequestMethod.GET)
    public BaseModelAndView getFileInsideView(){

        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("filemessage/fileinside");
        return modelAndView;
    }

    @RequestMapping(value = "/fileExternalView",method = RequestMethod.GET)
    public BaseModelAndView getFileExternalView(){

        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("filemessage/fileexternal");
        return modelAndView;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult upload(HttpServletRequest request){
        try {
            int type = Integer.parseInt(request.getParameter("type"));
            Session session = SecurityUtils.getSubject().getSession();
            Person person = (Person) session.getAttribute("MEMBER_USER_PERSON");
            //可能会有多个文件同时上传
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            iFileMessageService.uploadFile(files,person.getId(),person.getOrgId(),type);
            return renderSuccess("上传文件成功");
        }
        catch (ProgramException p) {
            log.error("上传文件失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("上传文件失败." + e.getMessage());
            return renderError("上传文件失败");
        }


    }

//    @RequestMapping("hello")
//    public String indexController(Model model){
//        model.addAttribute("imgsrc", "/image/34.jpg");
//        return "index";
//    }
}
