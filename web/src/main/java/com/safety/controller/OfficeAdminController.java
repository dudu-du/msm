package com.safety.controller;

import com.safety.tools.BaseController;
import com.safety.dto.user.OfficeAdminParams;
import com.safety.entity.Org;
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
import java.util.stream.Collectors;

/**
 * 教师接口
 */
@Controller
@Slf4j
@RequestMapping("View")
public class OfficeAdminController extends BaseController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private IOrgService orgService;

    /**
     * 局管理员列表
     * @return
     */
    @RequestMapping(value = "listOfficeAdmin", method = RequestMethod.GET)
    public BaseModelAndView showOfficeAdminList(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        //获取session
        Session session = SecurityUtils.getSubject().getSession();
        //获取人员
        ExtPerson person = (ExtPerson) session.getAttribute(DictConstants.MEMBER_USER_PERSON);
        modelAndView.addObject("orgId",person.getOrgId()==null?"":person.getOrgId());
        modelAndView.setViewName("/user/officeAdmin");
        return modelAndView;
    }

    /**
     * 模糊查询列表
     * @param officeAdminParams
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "listOfficeAdminInfo",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult listUser(OfficeAdminParams officeAdminParams,
                                        @RequestParam(value = "page") Integer page,
                                        @RequestParam(value = "pageSize") Integer pageSize){
        officeAdminParams.setPage(page);
        officeAdminParams.setPageSize(pageSize);
        officeAdminParams.setOrgId("");
        PageData list ;
        try {
            list = personService.getOfficeAdminListByScope(officeAdminParams);
        } catch (ProgramException e) {
            log.error("据管理员信息获取异常。",e);
            return renderError(e.getMessage());
        }
        return renderSuccess(list);
    }

    @RequestMapping(value="officeAdmin",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addUser(ExtPerson officeAdmin){
        try {
            officeAdmin.setPersonType(DictConstants.PERSON_OFFICEADMIN);
            officeAdmin.setId(UUIDUtil.getUUID());
            officeAdmin.setLoginId(UUIDUtil.getUUID());
            officeAdmin.setOrgId("0");
            officeAdmin.setCreatedatetime(LocalDateTime.now());
            officeAdmin.setModifydatetime(LocalDateTime.now());
            List<String> roleList = new ArrayList<>();
            roleList.add(DictConstants.ROLE_SUPERADMIN);
            officeAdmin.setRoleList(roleList);
            if(personService.saveEntity(officeAdmin)){
                return renderSuccess();
            }
        }catch (ProgramException e){
            log.error("局管理员添加失败【"+officeAdmin.toString()+"】",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("局管理员添加失败【"+officeAdmin.toString()+"】",e);
            return renderError("局管理员数据不合法");
        }
        return renderError("局管理员添加失败");
    }
    @RequestMapping(value="officeAdmin",method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult updateUser(ExtPerson officeAdmin){
        try {
            officeAdmin.setPersonType(DictConstants.PERSON_OFFICEADMIN);
            officeAdmin.setModifydatetime(LocalDateTime.now());
            if(personService.updateEntityById(officeAdmin)){
                return renderSuccess();
            }
        }catch (ProgramException e){
            log.error("局管理员修改失败【"+officeAdmin.toString()+"】",e);
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("局管理员修改失败【"+officeAdmin.toString()+"】",e);
            return renderError("局管理员数据不合法");
        }
        return renderError("局管理员修改失败");
    }
    @RequestMapping(value="officeAdmin",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult delUser(@RequestParam(value = "id") String id){
        try {
            if(personService.removeByIdStr(id)){
                return renderSuccess();
            }
        } catch (ProgramException e) {
            log.error("局管理员删除失败。",e);
            return renderError(e.getMessage());
        }
        return renderError("局管理员不存在");
    }



}
