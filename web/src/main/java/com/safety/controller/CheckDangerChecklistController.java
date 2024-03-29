package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDangerChecklist;
import com.safety.service.ICheckDangerChecklistService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * <p>
 * 隐患排查清单 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkDangerChecklist")
public class CheckDangerChecklistController extends BaseController {
    @Autowired
    private ICheckDangerChecklistService iCheckDangerChecklistService;
    /**
     * 添加
     * @param checkDangerChecklist
     * @return
     */
    @RequestMapping(value = "/checkDangerChecklist",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckDangerChecklist(@RequestBody CheckDangerChecklist checkDangerChecklist){
        String id = UUIDUtil.getUUID();
        checkDangerChecklist.setId(id);
        checkDangerChecklist.setCreateTime(LocalDateTime.now());
        boolean result = iCheckDangerChecklistService.save(checkDangerChecklist);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkDangerChecklist
     * @return
     */
    @RequestMapping(value = "/checkDangerChecklist",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckDangerChecklist(@RequestBody CheckDangerChecklist checkDangerChecklist){
        boolean result = iCheckDangerChecklistService.updateById(checkDangerChecklist);
        if (result){
            return renderSuccess("修改成功");
        }else {
            return renderError("修改失败");
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkDangerChecklist",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckDangerChecklist(String id){
        boolean result = iCheckDangerChecklistService.removeById(id);
        if (result){
            return renderSuccess("删除成功");
        }else {
            return renderError("删除失败");
        }
    }

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkDangerChecklist",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDangerChecklistById(String id){
        CheckDangerChecklist checkDangerChecklist = iCheckDangerChecklistService.getById(id);
        if(checkDangerChecklist!=null){
            return renderSuccess("查询成功",checkDangerChecklist);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 分页查询清单
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/checkDangerChecklistByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDangerChecklistByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize,@RequestParam(defaultValue="")String orgId){
        PageInfo<CheckDangerChecklist> page = iCheckDangerChecklistService.getByPage(currentPage, pageSize, orgId);
        if(page!=null){
            return renderSuccess("查询成功",page);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkDangerChecklistAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckDangerChecklistAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dangerChecklistAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkDangerChecklistEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckDangerChecklistEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dangerChecklistEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkDangerChecklistPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckDangerChecklistPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dangerChecklistPage");
        return modelAndView;
    }
    /**
     * 打印页面
     * @return
     */
    @RequestMapping(value = "/checkDangerChecklistPrint",method = RequestMethod.GET)
    public BaseModelAndView getCheckDangerChecklistPrint(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dangerChecklistPrint");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
