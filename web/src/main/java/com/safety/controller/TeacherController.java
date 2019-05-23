package com.safety.controller;

import com.safety.dto.user.TeacherParams;
import com.safety.entity.Org;
import com.safety.entity.Teacher;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * 教师接口
 */
@Controller
@Slf4j
@RequestMapping("View")
public class TeacherController extends BaseController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private IOrgService orgService;

    /**
     * 教师列表
     * @return
     */
    @RequestMapping(value = "listTeacher", method = RequestMethod.GET)
    public BaseModelAndView showTeacherList(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        //获取session
        Session session = SecurityUtils.getSubject().getSession();
        //获取人员
        ExtPerson person = (ExtPerson) session.getAttribute(DictConstants.MEMBER_USER_PERSON);
        modelAndView.addObject("orgId",person.getOrgId()==null?"":person.getOrgId());
        modelAndView.setViewName("/user/teacher");
        return modelAndView;
    }

    /**
     * 模糊查询列表
     * @param teacherParams
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "listTeacherInfo",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult listUser(TeacherParams teacherParams,
                                        @RequestParam(value = "page" ,required=false) Integer page,
                                        @RequestParam(value = "pageSize" ,required=false) Integer pageSize){
        boolean hasPage=false;
        if(page !=null && pageSize!=null){
            teacherParams.setPage(page);
            teacherParams.setPageSize(pageSize);
            hasPage=true;
        }
        if(teacherParams.getOrgId()==null || "0".equals(teacherParams.getOrgId())){//超级管理员
            teacherParams.setOrgId("");
        }
        PageData list = null;
        if(teacherParams.getOrgName()!=null && !"".equals(teacherParams.getOrgName())){
            List<String> orgIds = new ArrayList<>();
            try {
                List<Org> orgList=orgService.getOrgListBystr(DictConstants.ORGTYPE_SCHOOL,teacherParams.getOrgName());
                if(orgList == null || orgList.size() == 0){
                    return renderSuccess(new PageData<>(page,pageSize));
                }
                orgIds=orgList.stream().map(Org::getId).collect(Collectors.toList());
            } catch (Exception e) {
                log.error("获取机构异常",e);
            }
            teacherParams.setOrgIds(orgIds);
        }


        try {
            if(hasPage){
                list = personService.getTeahcherListByScope(teacherParams);
                return renderSuccess(list);
            }else{
                List<Teacher> teacherListNoPage = personService.getTeacherListNoPage(teacherParams);
                return renderSuccess(teacherListNoPage);
            }
        } catch (ProgramException e) {
            log.error("教师信息获取异常。",e);
            return renderError(e.getMessage());
        }

    }

    @RequestMapping(value="teacher",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addTeacher(ExtPerson teacher){
        try {
            teacher.setPersonType(DictConstants.PERSON_TEACHER);
            teacher.setId(UUIDUtil.getUUID());
            teacher.setLoginId(UUIDUtil.getUUID());
            teacher.setCreatedatetime(LocalDateTime.now());
            teacher.setModifydatetime(LocalDateTime.now());
            List<String> roleList = new ArrayList<>();
            roleList.add(DictConstants.ROLE_ORGADMIN);
            teacher.setRoleList(roleList);
            if(personService.saveEntity(teacher)){
                return renderSuccess();
            }
        }catch (ProgramException e){
            log.error("教师添加失败【"+teacher.toString()+"】",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("教师添加失败【"+teacher.toString()+"】",e);
            return renderError("教师数据不合法");
        }
        return renderError("教师添加失败");
    }
    @RequestMapping(value="teacher",method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult updateUser(ExtPerson teacher){
        try {
            teacher.setPersonType(DictConstants.PERSON_TEACHER);
            teacher.setModifydatetime(LocalDateTime.now());
            if(personService.updateEntityById(teacher)){
                return renderSuccess();
            }
        }catch (ProgramException e){
            log.error("教师修改失败【"+teacher.toString()+"】",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("教师修改失败【"+teacher.toString()+"】",e);
            return renderError("教师修改失败,未知异常");
        }
        return renderError("教师修改失败");
    }
    @RequestMapping(value="teacher",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult delUser(@RequestParam(value = "id") String id){
        try {
            if(personService.removeByIdStr(id)){
                return renderSuccess();
            }
        } catch (ProgramException e) {
            log.error("教师删除失败。",e);
            return renderError(e.getMessage());
        }
        return renderError("用户删除失败,用户不存在");
    }

    /**
     * 获取教师列表
     * @param orgId
     * @param teacherParams
     * @return
     */
    @RequestMapping(value="teacher",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getTeacherBySchoolId(@RequestParam(value = "orgId") String orgId,TeacherParams teacherParams){

        try {
            teacherParams.setOrgId(orgId);
            List<Teacher> teacherList = personService.getTeacherListNoPage(teacherParams);
            return renderSuccess(teacherList);
        } catch (ProgramException e) {
            log.error("获取教师列表失败");
            return renderError(e.getMessage());
        }
    }

}
