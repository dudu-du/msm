package com.safety.controller;


import com.safety.entity.CheckDangerChecklist;
import com.safety.service.ICheckDangerChecklistService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public JsonResult addCheckDangerChecklist(CheckDangerChecklist checkDangerChecklist){
        String id = UUIDUtil.getUUID();
        checkDangerChecklist.setId(id);
        boolean result = iCheckDangerChecklistService.save(checkDangerChecklist);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
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
    public JsonResult updateCheckDangerChecklist(CheckDangerChecklist checkDangerChecklist){
        boolean result = iCheckDangerChecklistService.updateById(checkDangerChecklist);
        if (result){
            return renderSuccess("修改成功");
        }else {
            return renderSuccess("修改失败");
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
            return renderSuccess("删除失败");
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
            return renderSuccess("无数据");
        }
    }
}
