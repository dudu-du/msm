package com.safety.controller;

import com.safety.dto.user.WorkerParams;
import com.safety.entity.Org;
import com.safety.entity.Worker;
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
 * 普通工作人员接口
 */
@Controller
@Slf4j
@RequestMapping("View")
public class WorkerController extends BaseController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private IOrgService orgService;

    /**
     * 普通工作人员列表
     * @return
     */
    @RequestMapping(value = "listWorker", method = RequestMethod.GET)
    public BaseModelAndView showWorderList(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        //获取session
        Session session = SecurityUtils.getSubject().getSession();
        //获取人员
        ExtPerson person = (ExtPerson) session.getAttribute(DictConstants.MEMBER_USER_PERSON);
        modelAndView.addObject("orgId",person.getOrgId()==null?"":person.getOrgId());
        modelAndView.setViewName("/user/worker");
        return modelAndView;
    }

    /**
     * 模糊查询列表
     * @param workerParams
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "listWorkerInfo",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult listUser(WorkerParams workerParams,
                               @RequestParam(value = "page" ,required=false) Integer page,
                               @RequestParam(value = "pageSize" ,required=false) Integer pageSize){
        boolean hasPage=false;
        if(page !=null && pageSize!=null){
            workerParams.setPage(page);
            workerParams.setPageSize(pageSize);
            hasPage=true;
        }
        if(workerParams.getOrgId()==null || "0".equals(workerParams.getOrgId())){//超级管理员
            workerParams.setOrgId("");
        }
        PageData list = null;
        if(workerParams.getOrgName()!=null && !"".equals(workerParams.getOrgName())){
            List<String> orgIds = new ArrayList<>();
            try {
                List<Org> orgList=orgService.getOrgListBystr(DictConstants.ORGTYPE_BUREAU, workerParams.getOrgName());
                if(orgList == null || orgList.size() == 0){
                    return renderSuccess(new PageData<>(page,pageSize));
                }
                orgIds=orgList.stream().map(Org::getId).collect(Collectors.toList());
            } catch (Exception e) {
                log.error("获取机构异常",e);
            }
            workerParams.setOrgIds(orgIds);
        }


        try {
            if(hasPage){
                list = personService.getWorkerListByScope(workerParams);
                return renderSuccess(list);
            }else{
                List<Worker> workerListNoPage = personService.getWorkerListNoPage(workerParams);
                return renderSuccess(workerListNoPage);
            }
        } catch (ProgramException e) {
            log.error("普通工作人员信息获取异常。",e);
            return renderError(e.getMessage());
        }

    }

    @RequestMapping(value="worker",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addWorker(ExtPerson worker){
        try {
            worker.setPersonType(DictConstants.PERSON_WORKER);
            worker.setId(UUIDUtil.getUUID());
            worker.setLoginId(UUIDUtil.getUUID());
            worker.setCreatedatetime(LocalDateTime.now());
            worker.setModifydatetime(LocalDateTime.now());
            List<String> roleList = new ArrayList<>();
            roleList.add(DictConstants.ROLE_WORKER);
            worker.setRoleList(roleList);
            if(personService.saveEntity(worker)){
                return renderSuccess();
            }
        }catch (ProgramException e){
            log.error("普通工作人员添加失败【"+worker.toString()+"】",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("普通工作人员添加失败【"+worker.toString()+"】",e);
            return renderError("普通工作人员数据不合法");
        }
        return renderError("普通工作人员添加失败");
    }
    @RequestMapping(value="worker",method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult updateUser(ExtPerson worker){
        try {
            worker.setPersonType(DictConstants.PERSON_WORKER);
            worker.setModifydatetime(LocalDateTime.now());
            if(personService.updateEntityById(worker)){
                return renderSuccess();
            }
        }catch (ProgramException e){
            log.error("普通工作人员修改失败【"+worker.toString()+"】",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("普通工作人员修改失败【"+worker.toString()+"】",e);
            return renderError("普通工作人员修改失败,未知异常");
        }
        return renderError("普通工作人员修改失败");
    }
    @RequestMapping(value="worker",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult delUser(@RequestParam(value = "id") String id){
        try {
            if(personService.removeByIdStr(id)){
                return renderSuccess();
            }
        } catch (ProgramException e) {
            log.error("普通工作人员删除失败。",e);
            return renderError(e.getMessage());
        }
        return renderError("用户删除失败,用户不存在");
    }

    /**
     * 获取普通工作人员列表
     * @param orgId
     * @param workerParams
     * @return
     */
    @RequestMapping(value="worker",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getWorkerBySchoolId(@RequestParam(value = "orgId") String orgId, WorkerParams workerParams){

        try {
            workerParams.setOrgId(orgId);
            List<Worker> workerList = personService.getWorkerListNoPage(workerParams);
            return renderSuccess(workerList);
        } catch (ProgramException e) {
            log.error("获取普通工作人员列表失败");
            return renderError(e.getMessage());
        }
    }

}
