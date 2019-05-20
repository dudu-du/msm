package com.welsee.controller;

import com.welsee.entity.Person;
import com.welsee.excel.util.StringUtils;
import com.welsee.exception.ProgramException;
import com.welsee.extentity.ExtPerson;
import com.welsee.service.IPersonService;
import com.welsee.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/View")
public class PersonController extends BaseController {

    @Autowired
    private IPersonService personService;

    //todo 用户信息页面
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public BaseModelAndView user() {
        //获取session中的角色
        Session session = SecurityUtils.getSubject().getSession();
        Person person = (Person) session.getAttribute("MEMBER_USER_PERSON");
        String orgId = person.getOrgId();
        String loginId = person.getLoginId();
        BaseModelAndView bmv = new BaseModelAndView();
        bmv.addObject("orgId", orgId);
        bmv.addObject("loginId", loginId);
        bmv.setViewName("user/userinfo");
        return bmv;
    }

    //todo 用户信息页面
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public BaseModelAndView info() {
        //获取session中的角色
        Session session = SecurityUtils.getSubject().getSession();
        Person person = (Person) session.getAttribute("MEMBER_USER_PERSON");
        String orgId = person.getOrgId();
        String loginId = person.getLoginId();
        BaseModelAndView bmv = new BaseModelAndView();
        bmv.addObject("orgId", orgId);
        bmv.addObject("loginId", loginId);
        bmv.addObject("org_name", "");

//        if(orgId != null && !StringUtils.isEmpty(orgId)){
//            try {
//                Map schoolMap = schoolService.getSchoolMap(orgId);
//                if(schoolMap != null) {
//                    bmv.addObject("org_name", schoolMap.get("name").toString());
//                }
//            }
//            catch (Exception e){
//            }
//        }

        bmv.setViewName("user/info");
        return bmv;
    }

    //todo 用户信息页面
    @RequestMapping(value = "changepassword", method = RequestMethod.GET)
    public BaseModelAndView changepassword() {
        //获取session中的角色
        Session session = SecurityUtils.getSubject().getSession();
        Person person = (Person) session.getAttribute("MEMBER_USER_PERSON");
        String orgId = person.getOrgId();
        String loginId = person.getLoginId();
        BaseModelAndView bmv = new BaseModelAndView();
        bmv.addObject("orgId", orgId);
        bmv.addObject("loginId", loginId);
        bmv.setViewName("user/password");
        return bmv;
    }

    /**
     * 获取用户信息(基本资料)
     * @param loginId
     * @return
     */
    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult userInfo(String loginId) {
        try {
            ExtPerson person = personService.selectByLoginId(loginId);
            return renderSuccess("获取用户信息成功", person);
        }catch (ProgramException e){
            log.error("获取用户信息失败." + e.getMessage());
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取用户信息失败." + e.getMessage());
            return renderError("获取用户信息失败");
        }
    }

    /**
     * 修改用户信息(基本资料)
     * @param person    loginId-必填项
     * @return
     */
    @RequestMapping(value="userInfo",method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult userInfo(ExtPerson person){
        person.setModifydatetime(LocalDateTime.now());
        try {
            if(person.getId()==null ||"".equals(person.getId())){
                ExtPerson extPerson = personService.selectByLoginId(person.getLoginId());
                person.setId(extPerson.getId());
            }
            if(personService.updateEntityById(person)){
                Session session = SecurityUtils.getSubject().getSession();
                ExtPerson extPerson = personService.selectByLoginId(person.getLoginId());
                session.setAttribute("MEMBER_USER_PERSON", extPerson);
                return renderSuccess();
            }
        } catch (ProgramException e) {
            log.error("修改用户信息失败",e.getMessage());
            renderError(e.getMessage());
        }
        return renderError("修改用户信息失败");
    }


    /**
     * 部门-人员列表
     * @return
     */
    @RequestMapping(value = "departmentUserList", method = RequestMethod.GET)
    public BaseModelAndView departmentUserList() {
        //获取session中的角色
        Session session = SecurityUtils.getSubject().getSession();
        Person person = (Person) session.getAttribute("MEMBER_USER_PERSON");
        String orgId = person.getOrgId();
        String loginId = person.getLoginId();
        BaseModelAndView bmv = new BaseModelAndView();
        bmv.addObject("orgId", orgId);
        bmv.addObject("loginId", loginId);
        bmv.setViewName("org/departmentUser");
        return bmv;
    }

}
