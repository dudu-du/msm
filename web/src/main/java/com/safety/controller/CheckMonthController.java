package com.safety.controller;


import com.safety.entity.CheckMonth;
import com.safety.service.ICheckMonthService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 月治理记录 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkMonth")
public class CheckMonthController extends BaseController {
    @Autowired
    private ICheckMonthService iCheckMonthService;
    /**
     * 添加
     * @param checkMonth
     * @return
     */
    @RequestMapping(value = "/checkMonth",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckMonth(@RequestBody CheckMonth checkMonth){
        boolean result = iCheckMonthService.addCheckMonth(checkMonth);
        if (result){
            return renderSuccess("添加成功");
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkMonth
     * @return
     */
    @RequestMapping(value = "/checkMonth",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckMonth(@RequestBody CheckMonth checkMonth){
        boolean result = iCheckMonthService.updateById(checkMonth);
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
    @RequestMapping(value = "/checkMonth",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckMonth(String id){
        boolean result = iCheckMonthService.removeById(id);
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
    @RequestMapping(value = "/checkMonthById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckMonthById(String id){
        CheckMonth checkMonth = iCheckMonthService.getById(id);
        if(checkMonth!=null){
            return renderSuccess("查询成功",checkMonth);
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
    @RequestMapping(value = "/checkMonth",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckMonthByParam(String orgId, String year){
        CheckMonth checkMonth = iCheckMonthService.getByParam(orgId,year);
        if(checkMonth!=null){
            return renderSuccess("查询成功",checkMonth);
        }else {
            return renderError("无数据");
        }
    }
}
