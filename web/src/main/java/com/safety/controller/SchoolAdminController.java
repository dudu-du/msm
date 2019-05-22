package com.safety.controller;

import com.safety.dto.user.SchoolAdminParams;
import com.safety.entity.Org;
import com.safety.exception.ProgramException;
import com.safety.extentity.ExtPerson;
import com.safety.service.IOrgService;
import com.safety.service.IPersonService;
import com.safety.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 校管理员接口
 */
@Controller
@Slf4j
@RequestMapping("View")
public class SchoolAdminController extends BaseController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private IOrgService orgService;

    /**
     * 学校资料页面
     * @return
     */
    @RequestMapping(value = "listSchoolAdmin", method = RequestMethod.GET)
    public BaseModelAndView showSchoolAdminList(){
        BaseModelAndView modelAndView = new BaseModelAndView();
//        //获取session
//        Session session = SecurityUtils.getSubject().getSession();
//        //获取人员
//        ExtPerson person = (ExtPerson) session.getAttribute(DictConstants.MEMBER_USER_PERSON);
//        modelAndView.addObject("orgId",person.getOrgId()==null?"":person.getOrgId());
        modelAndView.setViewName("/user/schoolAdmin");
        return modelAndView;
    }

    /**
     * 部门管理页面
     * @return
     */
    @RequestMapping(value = "listDepartmentAdmin", method = RequestMethod.GET)
    public BaseModelAndView showDepartmentAdminList(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("/org/departmentAdmin");
        return modelAndView;
    }

    /**
     * 模糊查询列表
     * @param schoolAdminParams
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "listSchoolAdminInfo",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult listUser(SchoolAdminParams schoolAdminParams,
                                        @RequestParam(value = "page") Integer page,
                                        @RequestParam(value = "pageSize") Integer pageSize){
        schoolAdminParams.setPage(page);
        schoolAdminParams.setPageSize(pageSize);
        if(schoolAdminParams.getOrgId()==null || "0".equals(schoolAdminParams.getOrgId())){//超级管理员
            schoolAdminParams.setOrgId("");
        }
        PageData list = null;
        if(schoolAdminParams.getOrgName()!=null && !"".equals(schoolAdminParams.getOrgName())){
            List<String> orgIds = new ArrayList<>();
            try {
                List<Org> orgList=orgService.getOrgListBystr(DictConstants.ORGTYPE_SCHOOL,schoolAdminParams.getOrgName());
                if(orgList == null || orgList.size() == 0){
                    return renderSuccess(new PageData<>(page,pageSize));
                }
                orgIds=orgList.stream().map(Org::getId).collect(Collectors.toList());
            } catch (Exception e) {
                log.error("获取机构异常",e);
            }
            schoolAdminParams.setOrgIds(orgIds);
        }
        try {
            list = personService.getSchoolAdminListByScope(schoolAdminParams);
        } catch (ProgramException e) {
            log.error("据管理员信息获取异常。",e);
            return renderError(e.getMessage());
        }
        return renderSuccess(list);
    }

    @RequestMapping(value="schoolAdmin",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addUser(ExtPerson schoolAdmin){
        try {
            schoolAdmin.setPersonType(DictConstants.PERSON_TEACHER);
            schoolAdmin.setId(UUIDUtil.getUUID());
            schoolAdmin.setLoginId(UUIDUtil.getUUID());
            schoolAdmin.setCreatedatetime(LocalDateTime.now());
            schoolAdmin.setModifydatetime(LocalDateTime.now());
            List<String> roleList = new ArrayList<>();
            roleList.add(DictConstants.ROLE_SHCOOLADMIN);
            schoolAdmin.setRoleList(roleList);
            if(personService.saveEntity(schoolAdmin)){
                return renderSuccess();
            }
        }catch (ProgramException e){
            log.error(e.getMessage());
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("校管理员添加失败【"+schoolAdmin.toString()+"】",e);
            return renderError("校管理员数据不合法");
        }
        return renderError("校管理员添加失败");
    }
    @RequestMapping(value="schoolAdmin",method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult updateUser(ExtPerson schoolAdmin){
        try {
            schoolAdmin.setPersonType(DictConstants.PERSON_TEACHER);
            schoolAdmin.setModifydatetime(LocalDateTime.now());
            if(personService.updateEntityById(schoolAdmin)){
                return renderSuccess();
            }
        } catch (Exception e) {
            log.error("校管理员修改失败【"+schoolAdmin.toString()+"】",e);
            return renderError(e.getMessage());
        }
        return renderError("校管理员修改失败");
    }
    @RequestMapping(value="schoolAdmin",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult delUser(@RequestParam(value = "id") String id){
        try {
            if(personService.removeByIdStr(id)){
                return renderSuccess();
            }
        } catch (ProgramException e) {
            log.error("校管理员删除失败。",e);
            return renderError(e.getMessage());
        }catch (Exception e){
            log.error("校管理员删除失败。",e);
            return renderError("校管理员删除失败");
        }
        return renderError("校管理员不存在");
    }



}
