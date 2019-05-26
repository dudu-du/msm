package com.safety.controller;


import com.safety.entity.CheckSpecial;
import com.safety.service.ICheckSpecialService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 专项检查 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkSpecial")
public class CheckSpecialController extends BaseController {
    @Autowired
    private ICheckSpecialService iCheckSpecialService;
    /**
     * 添加
     * @param checkSpecial
     * @return
     */
    @RequestMapping(value = "/checkSpecial",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckSpecial(@RequestBody CheckSpecial checkSpecial){
        boolean result = iCheckSpecialService.addCheckSpecial(checkSpecial);
        if (result){
            return renderSuccess("添加成功");
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkSpecial
     * @return
     */
    @RequestMapping(value = "/checkSpecial",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckSpecial(@RequestBody CheckSpecial checkSpecial){
        boolean result = iCheckSpecialService.updateById(checkSpecial);
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
    @RequestMapping(value = "/checkSpecial",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckSpecial(String id){
        boolean result = iCheckSpecialService.removeById(id);
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
    @RequestMapping(value = "/checkSpecialById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSpecialById(String id){
        CheckSpecial checkSpecial = iCheckSpecialService.getById(id);
        if(checkSpecial!=null){
            return renderSuccess("查询成功",checkSpecial);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 根据日期和机构名称查询
     * @param orgId
     * @param year
     * @return
     */
    @RequestMapping(value = "/checkWeek",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSpecialByParam(String orgId, String year){
        CheckSpecial checkSpecial = iCheckSpecialService.getByParam(orgId,year);
        if(checkSpecial!=null){
            return renderSuccess("查询成功",checkSpecial);
        }else {
            return renderError("无数据");
        }
    }
}
