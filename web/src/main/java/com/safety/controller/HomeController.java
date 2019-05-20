package com.safety.controller;

import com.alibaba.fastjson.JSONArray;
import com.safety.excel.util.StringUtils;
import com.safety.exception.ProgramException;
import com.safety.extentity.ExtPerson;
import com.safety.service.IMenuService;
import com.safety.shiro.GlobalDefaultExceptionHandler;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
public class HomeController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IMenuService menuService;
    @Autowired
    GlobalDefaultExceptionHandler globalDefaultExceptionHandler;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public BaseModelAndView index(BaseModelAndView bmv, HttpServletRequest request, HttpServletResponse response) {

        if(bmv==null){
            bmv = new BaseModelAndView();
        }
        Session session = SecurityUtils.getSubject().getSession();
        String orgName = "";
        String orgNameCn = "";
        String orgPic = "";
        String orgIcon = "";
        String orgDesc = "";
        String orgFont = "";
        String fontColour = "";
        Object orgId = request.getAttribute("orgId");
        if(orgId != null && !StringUtils.isEmpty(orgId)){
//            try {
//                Map schoolMap = schoolService.getSchoolMap(orgId.toString());
//                if(schoolMap != null) {
//                    orgName = schoolMap.get("name").toString();
//                    orgNameCn = schoolMap.get("schoolCn").toString();
//                    orgPic = schoolMap.get("schoolImg").toString();
//                    orgIcon = schoolMap.get("schoolIcon").toString();
//                    orgDesc = schoolMap.get("schoolProfile").toString();
//                    orgFont = schoolMap.get("schoolFont").toString();
//                    fontColour = schoolMap.get("fontColour").toString();
//                }
//            }
//            catch (Exception e){
//                logger.error("获取机构信息失败"+e.getMessage());
//            }
        }
        if(session.getAttribute("MEMBER_USER_KEY")==null) {
            logger.info("没有用户登录跳转到登录界面");
            if(globalDefaultExceptionHandler.isAjax(request)){
                try {
                    globalDefaultExceptionHandler.onLoginFail(response);
                }
                catch (Exception e){

                }
            }else {
                bmv.addObject("client_id", "");
                bmv.addObject("redirect_uri", "");
                bmv.addObject("picture_uuid", UUIDUtil.getUUID());
                if(orgId == null || StringUtils.isEmpty(orgId)){
                    bmv.setViewName("safety/login");
                }else {
                    bmv.addObject("school_name", orgName);
                    bmv.addObject("school_namecn", orgNameCn);
                    bmv.addObject("school_pic", orgPic);
                    bmv.addObject("school_icon", orgIcon);
                    bmv.addObject("school_desc",orgDesc);
                    bmv.addObject("school_font",orgFont);
                    bmv.addObject("font_colour",fontColour);
                    bmv.setViewName("safety/schoolLogin");
                }
            }
        }else{
            ExtPerson person = (ExtPerson) session.getAttribute("MEMBER_USER_PERSON");
            List<String> roleList = person.getRoleList();
            bmv.addObject("org_name",orgName);
            bmv.addObject("school_namecn", orgNameCn);
            bmv.addObject("org_pic",orgPic);
            bmv.addObject("school_icon",orgIcon);
            bmv.addObject("school_desc",orgDesc);
            bmv.addObject("school_font",orgFont);
            bmv.addObject("font_colour",fontColour);
            if(roleList.contains("ROLE_ORGADMIN") || roleList.contains("ROLE_SUPERADMIN")) {
                logger.info("用户已登录跳转到总界面，登录用户id为：" + session.getAttribute("MEMBER_USER_KEY"));
                bmv.setViewName("home/index");
            }
            else{
                logger.info("用户已登录跳转到模块界面，登录用户id为：" + session.getAttribute("MEMBER_USER_KEY"));
                bmv.setViewName("home/product");
            }
        }
        return bmv;
    }

    @RequestMapping(value = "/base",method = RequestMethod.GET)
    public BaseModelAndView base(BaseModelAndView bmv, HttpServletRequest request, HttpServletResponse response) {
        if(bmv==null){
            bmv = new BaseModelAndView();
        }
        String orgName = "";
        String orgNameCn = "";
        String orgPic = "";
        String orgIcon = "";
        String orgDesc = "";
        String orgFont = "";
        String fontColour = "";
        Object orgId = request.getAttribute("orgId");
        if(orgId != null && !StringUtils.isEmpty(orgId)){
//            try {
//                Map schoolMap = schoolService.getSchoolMap(orgId.toString());
//                if(schoolMap != null) {
//                    orgName = schoolMap.get("name").toString();
//                    orgNameCn = schoolMap.get("schoolCn").toString();
//                    orgPic = schoolMap.get("schoolImg").toString();
//                    orgIcon = schoolMap.get("schoolIcon").toString();
//                    orgDesc = schoolMap.get("schoolProfile").toString();
//                    orgFont = schoolMap.get("schoolFont").toString();
//                    fontColour = schoolMap.get("fontColour").toString();
//                }
//            }
//            catch (Exception e){
//                logger.error("获取机构信息失败"+e.getMessage());
//            }
        }
        bmv.addObject("org_name",orgName);
        bmv.addObject("school_namecn", orgNameCn);
        bmv.addObject("org_pic",orgPic);
        bmv.addObject("school_icon",orgIcon);
        bmv.addObject("school_desc",orgDesc);
        bmv.addObject("school_font",orgFont);
        bmv.addObject("font_colour",fontColour);
        bmv.setViewName("home/index");
        return bmv;
    }

    @RequestMapping(value = "user")
    @ResponseBody
    public JsonResult user(){
        return renderSuccess(null);
    }


    @RequestMapping(value = "menu")
    @ResponseBody
    public JsonResult menu(){
        try {
            Session session = SecurityUtils.getSubject().getSession();
            ExtPerson person = (ExtPerson) session.getAttribute("MEMBER_USER_PERSON");
            JSONArray menuTree = menuService.getMenuByUser(person);
            return renderSuccess("获取菜单树成功",menuTree);
        }
        catch (ProgramException p) {
            log.error("获取菜单树失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("获取菜单树失败." + e.getMessage());
            return renderError("获取菜单树失败");
        }
    }

}

