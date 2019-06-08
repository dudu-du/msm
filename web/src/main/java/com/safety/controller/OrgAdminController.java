package com.safety.controller;

import com.safety.tools.BaseController;
import com.safety.dto.user.OrgAdminParams;
import com.safety.exception.ProgramException;
import com.safety.extentity.ExtPerson;
import com.safety.service.IOrgService;
import com.safety.service.IPersonService;
import com.safety.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 教师接口
 */
@Controller
@Slf4j
@RequestMapping("View")
public class OrgAdminController extends BaseController {

    @Autowired
    private IPersonService personService;

    /**
     * 机构管理员列表
     * @return
     */
    @RequestMapping(value = "listOrgAdmin", method = RequestMethod.GET)
    public BaseModelAndView showOrgAdminList(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        //获取session
        Session session = SecurityUtils.getSubject().getSession();
        //获取人员
        ExtPerson person = (ExtPerson) session.getAttribute(DictConstants.MEMBER_USER_PERSON);
        modelAndView.addObject("orgId",person.getOrgId()==null?"":person.getOrgId());
        modelAndView.setViewName("/user/orgAdmin");
        return modelAndView;
    }

    /**
     * 模糊查询列表
     * @param orgAdminParams
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "listOrgAdminInfo",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult listUser(OrgAdminParams orgAdminParams,
                               @RequestParam(value = "page") Integer page,
                               @RequestParam(value = "pageSize") Integer pageSize){
        orgAdminParams.setPage(page);
        orgAdminParams.setPageSize(pageSize);
        orgAdminParams.setOrgId("");
        PageData list ;
        try {
            list = personService.getOrgAdminListByScope(orgAdminParams);
        } catch (ProgramException e) {
            log.error("据管理员信息获取异常。",e);
            return renderError(e.getMessage());
        }
        return renderSuccess(list);
    }

    @RequestMapping(value="orgAdmin",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addUser(ExtPerson orgAdmin){
        try {
            orgAdmin.setPersonType(DictConstants.PERSON_OFFICEADMIN);
            orgAdmin.setId(UUIDUtil.getUUID());
            orgAdmin.setLoginId(UUIDUtil.getUUID());
//            officeAdmin.setOrgId("0");
            orgAdmin.setCreatedatetime(LocalDateTime.now());
            orgAdmin.setModifydatetime(LocalDateTime.now());
            List<String> roleList = new ArrayList<>();
            roleList.add(DictConstants.ROLE_ORGADMIN);
            orgAdmin.setRoleList(roleList);
            if(personService.saveEntity(orgAdmin)){
                return renderSuccess();
            }
        }catch (ProgramException e){
            log.error("机构管理员添加失败【"+orgAdmin.toString()+"】",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("机构管理员添加失败【"+orgAdmin.toString()+"】",e);
            return renderError("机构管理员数据不合法");
        }
        return renderError("机构管理员添加失败");
    }
    @RequestMapping(value="orgAdmin",method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult updateUser(ExtPerson orgAdmin){
        try {
            orgAdmin.setPersonType(DictConstants.PERSON_OFFICEADMIN);
            orgAdmin.setModifydatetime(LocalDateTime.now());
            if(personService.updateEntityById(orgAdmin)){
                return renderSuccess();
            }
        }catch (ProgramException e){
            log.error("机构管理员修改失败【"+orgAdmin.toString()+"】",e);
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("机构管理员修改失败【"+orgAdmin.toString()+"】",e);
            return renderError("机构管理员数据不合法");
        }
        return renderError("机构管理员修改失败");
    }
    @RequestMapping(value="orgAdmin",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult delUser(@RequestParam(value = "id") String id){
        try {
            if(personService.removeByIdStr(id)){
                return renderSuccess();
            }
        } catch (ProgramException e) {
            log.error("机构管理员删除失败。",e);
            return renderError(e.getMessage());
        }
        return renderError("机构管理员不存在");
    }



}
